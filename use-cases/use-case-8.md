
# USE CASE: 8 delete an employee's details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an HR advisor I want to delete an employee's details so that the company is compliant with data retention legislation.
### Scope

Company.

### Level

Primary task.

### Preconditions

Details of the  employee.  Database containing employee's details

### Success End Condition

Employee details updated.

### Failed End Condition

Employee details not updated.

### Primary Actor

HR Advisor.

### Trigger

A request for deletion employee's details is sent to HR.

## MAIN SUCCESS SCENARIO

1. Request is send to HR to remove employee details.
2. HR advisor finds employee in the database.
3. HR advisor removes employee details.
4. HR advisor completes records update.

## EXTENSIONS

2. Employee not in Database
3. HR advisor completes records update.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
