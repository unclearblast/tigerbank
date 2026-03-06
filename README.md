# TigerBank Finance Accounting Module

Консольное приложение модуля **«Учёт финансов»** для системы **ТигрБанк**.

Проект реализует доменную модель финансового учета с использованием принципов **SOLID**, **GRASP**, **DI-контейнера Spring** и паттернов **GoF**.

Основная задача — демонстрация архитектурного подхода к проектированию системы финансового учета.

---

# Предметная область

Программа моделирует модуль **учета личных финансов**.

Пользователь может:

- создавать счета
- создавать категории доходов и расходов
- добавлять операции (доход / расход)
- анализировать финансы
- импортировать и экспортировать данные

Система хранит:

- счета (BankAccount)
- категории (Category)
- операции (Operation)

---

# Реализованные функциональные требования

## Управление доменной моделью

CRUD операции:

### Счета
- создание
- редактирование
- удаление
- просмотр

### Категории
- создание
- редактирование
- удаление
- просмотр

### Операции
- создание доходов
- создание расходов
- удаление
- просмотр

---

# Аналитика

Система поддерживает:

- расчет прибыли за период
- группировку операций по категориям
- анализ расходов
- пересчет баланса счета

---

# Импорт и экспорт данных

Поддерживаемые форматы:

- JSON
- CSV
- YAML

Пользователь может выбрать формат в консольном интерфейсе.


1 - JSON
2 - CSV
3 - YAML


Экспортируются:

- счета
- операции

Импорт позволяет восстановить данные системы.

---

# Архитектура

Проект реализован по принципам:

## SOLID

### Single Responsibility
Каждый класс отвечает за одну задачу.

### Open/Closed
Расширение системы происходит через новые реализации интерфейсов.

### Liskov Substitution
Все реализации сервисов взаимозаменяемы.

### Interface Segregation
Интерфейсы разделены по ролям.

### Dependency Inversion
Зависимости идут от абстракций.

---

# GRASP

Используются:

### High Cohesion
Классы имеют узкую область ответственности.

### Low Coupling
Компоненты взаимодействуют через интерфейсы.

---

# Используемые паттерны GoF

## Factory Method

Создание доменных объектов:


OperationFactory
AccountFactory
CategoryFactory


Позволяет изолировать создание объектов.

---

## Facade

Фасады упрощают работу с подсистемами:


AccountFacade
CategoryFacade
OperationFacade
AnalyticsFacade


Фасады объединяют работу сервисов и репозиториев.

---

## Command

Каждый пользовательский сценарий реализован как команда.

Примеры:


CreateAccountCommand
AddOperationCommand
ExportDataCommand
ImportDataCommand


Команды вызываются из ConsoleApplication.

---

## Template Method

Используется при импорте данных.

Абстрактный класс:


AbstractDataImporter


Конкретные реализации:


JsonDataImporter
CsvDataImporter
YamlDataImporter


Общий алгоритм импорта:


loadFile()
parseData()
processData()
saveData()


---

# Dependency Injection

В проекте используется **Spring DI контейнер**.

Конфигурация:


AppConfig


Бины:

- сервисы
- репозитории
- импортеры
- фасады

Это уменьшает связанность компонентов.

---

# Структура проекта


ru.tigrbank.finance

config
└ AppConfig

console
└ ConsoleApplication

domain
├ entity
│ ├ BankAccount
│ ├ Category
│ └ Operation
│
├ enums
│ └ OperationType
│
└ repository
├ Repository
└ InMemoryRepository

application

├ facade
│ ├ AccountFacade
│ ├ CategoryFacade
│ ├ OperationFacade
│ └ AnalyticsFacade
│
├ factory
│ ├ AccountFactory
│ ├ CategoryFactory
│ └ OperationFactory
│
├ command
│ ├ Command
│ ├ CreateAccountCommand
│ ├ AddOperationCommand
│ ├ ExportDataCommand
│ └ ImportDataCommand
│
├ service
│ ├ OperationService
│ ├ AnalyticsService
│ └ BalanceService
│
├ importexport
│ ├ DataTransferService
│ ├ ImportResult
│ │
│ ├ importer
│ │ ├ AbstractDataImporter
│ │ ├ JsonDataImporter
│ │ ├ CsvDataImporter
│ │ └ YamlDataImporter
│ │
│ └ exporter
│ ├ JsonExporter
│ ├ CsvExporter
│ └ YamlExporter


---

# UML диаграмма (для draw.io)


BankAccount

id

name

balance

Category

id

name

type

Operation

id

accountId

categoryId

amount

date

type

AccountFacade
OperationFacade
CategoryFacade
AnalyticsFacade

OperationFactory
CategoryFactory
AccountFactory

Command
CreateAccountCommand
AddOperationCommand
ExportDataCommand
ImportDataCommand

AbstractDataImporter
├ JsonDataImporter
├ CsvDataImporter
└ YamlDataImporter


---

# Когда архитектура может усложниться

Проблемы могут возникнуть:

- при добавлении новых типов операций
- при добавлении новых форматов импорта
- при переходе на базу данных

Однако благодаря паттернам:

Factory  
Facade  
Command  
Template Method  

система легко расширяется.

---

# Почему введенные абстракции улучшают дизайн

1. **Фабрики** изолируют создание объектов
2. **Фасады** упрощают использование подсистем
3. **Команды** позволяют инкапсулировать пользовательские сценарии
4. **Template Method** устраняет дублирование кода

В результате архитектура становится:

- расширяемой
- тестируемой
- поддерживаемой

---

# Модульное тестирование

Используется **JUnit**.

Покрываются:

- фабрики
- фасады
- операции
- аналитика

---

# Запуск проекта

## Требования

- Java 17+
- IntelliJ IDEA

---

## Шаг 1

Открыть проект в IntelliJ


File → Open


---

## Шаг 2

Обновить зависимости


Reload Maven Project


---

## Шаг 3

Запустить


ConsoleApplication


---

## Пример работы


Текущий баланс: 97500

Прибыль за месяц: 97500

Выберите формат:

1 - JSON
2 - CSV
3 - YAML


После выбора система выполнит:

- экспорт данных
- импорт данных
- вывод статистики

---
