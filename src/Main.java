import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Инициализация базы данных в ОЗУ
        Subject javaProg = new Subject(1, "Программно-аппаратные системы", 72);
        Student student = new Student(10, "Маттаис А. Ю.", "u24-00-078471", "ЗАЧ-555", "ИТ-24");
        Teacher teacher = new Teacher(1, "Барановский Ю. А.", "t-1000", "Профессор", "к.т.н.");
        DeanEmployee dean = new DeanEmployee(100, "Тимофеев И. С.", "d-1000", "Деканат ИТ");

        Statement currentStatement = null;

        System.out.println("=== СИСТЕМА УЧЕТА (ГЛАВНАЯ ВЕТКА) ===");

        while (true) {
            System.out.println("\nГЛАВНОЕ МЕНЮ СИСТЕМЫ:");
            System.out.println("1. Войти как ДЕКАНАТ");
            System.out.println("2. Войти как ПРЕПОДАВАТЕЛЬ");
            System.out.println("3. Войти как СТУДЕНТ");
            System.out.println("0. Завершить работу");
            System.out.print("Выберите роль > ");

            String cmd = sc.nextLine().trim();

            if (cmd.equals("1")) {
                try {
                    System.out.print("Введите логин сотрудника деканата: ");
                    String loginInput = sc.nextLine();
                    dean.authorize(loginInput);

                    // Внутренний цикл для сессии декана
                    while (true) {
                        System.out.println("\n[Деканат: " + dean.fullName + "] Выберите операцию:");
                        System.out.println("а) Создать ведомость");
                        System.out.println("б) Направить приказ об отчислении");
                        System.out.println("в) Выйти из аккаунта");
                        System.out.print("Ваш выбор > ");
                        String subCmd = sc.nextLine().trim();

                        if (subCmd.equalsIgnoreCase("а")) {
                            currentStatement = dean.createStatement(1, javaProg);
                        } else if (subCmd.equalsIgnoreCase("б")) {
                            dean.expelStudent(student);
                        } else if (subCmd.equalsIgnoreCase("в")) {
                            System.out.println("Выход из аккаунта деканата выполнен.");
                            break; // Выход из вложенного цикла сессии
                        } else {
                            System.out.println("Ошибка: неверная операция! Попробуйте еще раз.");
                        }
                    }
                } catch (UnauthorizedAccessException e) {
                    System.out.println("[ОТКАЗАНО В ДОСТУПЕ]: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("[ОШИБКА ДЕКАНАТА]: " + e.getMessage());
                }
            }
            else if (cmd.equals("2")) {
                try {
                    System.out.print("Введите логин преподавателя: ");
                    String loginInput = sc.nextLine();
                    teacher.authorize(loginInput);

                    // Внутренний цикл для сессии преподавателя
                    while (true) {
                        System.out.println("\n[Преподаватель: " + teacher.fullName + "] Выберите операцию:");
                        System.out.println("а) Отметить присутствие студента на паре");
                        System.out.println("б) Выставить оценку за экзамен");
                        System.out.println("в) Закрыть ведомость");
                        System.out.println("г) Выйти из аккаунта");
                        System.out.print("Ваш выбор > ");
                        String subCmd = sc.nextLine().trim();

                        if (subCmd.equalsIgnoreCase("а")) {
                            try {
                                Lesson l1 = new Lesson(1, "2026-06-10", "Использование Git", javaProg);
                                teacher.markAttendance(student, l1, true);
                            } catch (Exception e) {
                                System.out.println("[ОШИБКА]: " + e.getMessage());
                            }
                        } else if (subCmd.equalsIgnoreCase("б")) {
                            try {
                                System.out.print("Введите экзаменационный балл (от 2 до 5): ");
                                String rawBall = sc.nextLine().trim();
                                if (rawBall.isEmpty()) {
                                    throw new IllegalArgumentException("Балл не может быть пустым!");
                                }
                                int val = Integer.parseInt(rawBall);
                                teacher.setGrade(student, javaProg, val, currentStatement);
                            } catch (NumberFormatException e) {
                                System.out.println("[ОШИБКА ВВОДА]: Введено некорректное число.");
                            } catch (Exception e) {
                                System.out.println("[ОШИБКА]: " + e.getMessage());
                            }
                        } else if (subCmd.equalsIgnoreCase("в")) {
                            try {
                                if (currentStatement == null) {
                                    throw new EntityNotFoundException("Ведомость еще не создана деканатом.");
                                }
                                currentStatement.closeStatement();
                                System.out.println("[Успешно] Ведомость закрыта.");
                            } catch (Exception e) {
                                System.out.println("[ОШИБКА]: " + e.getMessage());
                            }
                        } else if (subCmd.equalsIgnoreCase("г")) {
                            System.out.println("Выход из аккаунта преподавателя выполнен.");
                            break; // Выход из вложенного цикла сессии
                        } else {
                            System.out.println("Ошибка: неверный выбор. Попробуйте еще раз.");
                        }
                    }
                } catch (UnauthorizedAccessException e) {
                    System.out.println("[ОТКАЗАНО В ДОСТУПЕ]: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("[ОШИБКА РАБОТЫ]: " + e.getMessage());
                }
            }
            else if (cmd.equals("3")) {
                try {
                    System.out.print("Введите логин студента: ");
                    String loginInput = sc.nextLine();
                    student.authorize(loginInput);

                    // Внутренний цикл для сессии студента
                    while (true) {
                        System.out.println("\n[Студент: " + student.fullName + "] Выберите операцию:");
                        System.out.println("а) Загрузить отчет по ЛР");
                        System.out.println("б) Посмотреть свои оценки");
                        System.out.println("в) Выйти из аккаунта");
                        System.out.print("Ваш выбор > ");
                        String subCmd = sc.nextLine().trim();

                        if (subCmd.equalsIgnoreCase("а")) {
                            student.submitLab();
                        } else if (subCmd.equalsIgnoreCase("б")) {
                            student.getGrades();
                        } else if (subCmd.equalsIgnoreCase("в")) {
                            System.out.println("Выход из аккаунта студента выполнен.");
                            break; // Выход из вложенного цикла сессии
                        } else {
                            System.out.println("Ошибка: неверный выбор. Попробуйте еще раз.");
                        }
                    }
                } catch (UnauthorizedAccessException e) {
                    System.out.println("[ОТКАЗАНО В ДОСТУПЕ]: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("[ОШИБКА СТУДЕНТА]: " + e.getMessage());
                }
            }
            else if (cmd.equals("0")) {
                System.out.println("Завершение сеанса работы...");
                break;
            }
            else {
                System.out.println("Неверная роль! Попробуйте еще раз.");
            }
        }
    }
}