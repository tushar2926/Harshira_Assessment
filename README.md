# Harshira_Assessment
# Harshira_Assessment

Solution for the Hashira Placement Assignment.

## Problem
- Input is provided in JSON format with:
  - `n`: number of roots
  - `k`: number of roots to use
  - `"base"` and `"value"` for each root
- Task:
  1. Convert values from given base to decimal
  2. Take the first `k` roots
  3. Form polynomial `(x - r1)(x - r2)...(x - rk)`
  4. Print polynomial coefficients

## Files
- `HashiraAssignment.java` → Java code
- `testcase1.json` → Small test case
- `testcase2.json` → Large test case

## How to Run
```bash
javac HashiraAssignment.java
java HashiraAssignment testcase1.json
java HashiraAssignment testcase2.json

