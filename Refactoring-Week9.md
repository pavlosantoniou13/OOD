# Week 9 Refactoring Review Documentation

## Targeted Architectural Code Smells
Before refactoring, the `getRecyclingGuidance()` method inside the `Product` domain class exhibited several design issues:
1. **Long Method Smell**: It handled text configuration, conditional state routing, and complex formatting strings simultaneously.
2. **Violations of Single Responsibility Principle (SRP)**: The domain object managed calculation routing alongside complex string building.

## Applied Remediation Techniques
We resolved these issues using the **Extract Method** pattern:
* Isolating processing routines into small private sub-methods (`createEmptyGuidance`, `createSingleMaterialGuidance`, and `createMixedMaterialGuidance`).

## Real-World Benefits
* **Improved Cohesion**: `Product` now delegates specific presentation logic to targeted helper sub-routines.
* **Simplified Maintenance**: Formatting errors can now be fixed within individual helper methods without risking regressions in overall calculation routing logic.