# OOD-Project

## Project Overview
This system is designed to manage products and reusable materials while calculating their environmental impact and providing recycling guidance. It focuses on sustainable production and consumption patterns in alignment with Sustainable Development Goal 12.

## System Purpose
The application provides a maintainable and testable object-oriented solution to:
* Manage a registry of products and reusable material definitions.
* Calculate environmental impact using interchangeable calculation strategies.
* Provide specific recycling guidance for single-material and mixed-material products.

---

## Team Roles
- **[Pavlos Antoniou]**: Repo owner, Tester 
- **[Layth Al Mardini]**: Documentation, Tester
- **[Rabie Al Khoja]**: Documentation, Tester

---

## Domain Concepts
| Concept | Classification | Responsibility |
| :--- | :--- | :--- |
| **Product** | Entity | Represents a consumer item, storing its name, category, and estimated lifespan. |
| **Material** | Entity | Defines a reusable substance with environmental impact data and recycling instructions. |
| **EnvironmentalImpactStrategy** | Interface | Defines the contract for interchangeable environmental impact calculation algorithms. |
| **ImpactCalculator** | Service | Implements the algorithm used to calculate environmental impact. |
| **ProductRegistry** | Service | Manages storage, retrieval, and lifecycle of products and materials. |
| **RecyclingGuide** | Service | Evaluates product composition to provide recycling instructions. |
| **Menu** | Boundary | Provides a console-based interface for user interaction with the system. |

---

## Professional Git Workflow
To ensure code quality and stability, the team adheres to the following workflow:

1. **Main Branch Protection:** The main branch is protected to ensure stability.
2. **Branching Strategy:** All development is performed on dedicated feature branches.
3. **Pull Requests:** Code is merged via pull requests only after the CI build and tests pass.
4. **Commit Standards:** Commits must be meaningful and clearly reflect the changes made.

---

## Branch Naming Convention

To keep the repository organized and easy to navigate, these branch naming conventions will be followed:

| Type         | Format                         | Example                          |
|--------------|--------------------------------|----------------------------------|
| Feature      | `feature/name-of-feature`      | `feature/add-product-registry`   |
| Bug Fix      | `bug/fixed-bug`                | `bug/fix-calculation-error`      |
| Development  | `development`                 | Main integration branch          |
---