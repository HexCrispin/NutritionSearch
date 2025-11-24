# Testing plan:
## 1: Test the Factory

Ensure a test for each case enum (ensure the correct loader is chosen)

## 2: Test the loaders

Ensure each loader given valid data parses

Ensure each loader fails on startup with invalid data

Ensure Invalid data is handled responsibly

### 2a: Why it failed support?

Is this something required?

## 3: Test the parse to service

Ensure that the correct data is parsed to the service

## 4: Split tests into unit and integration tests

Test cases for valid search, sort and limit ensuring is handled correctly

Test cases for invalid search terms ensuring exceptions are handled correctly

Rewrite tests for testing business logic
