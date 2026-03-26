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
- **[Rabie Al Khoja]**: -

---

## Domain Concepts
| Concept | Classification | Responsibility |
| :--- | :--- | :--- |
| **Product** | Entity | Maintains identity and tracks its material composition. |
| **Material** | Entity | Defines specific environmental data and disposal instructions. |
| **ImpactStrategy** | Service | Defines the interface for interchangeable calculation algorithms. |
| **RecyclingGuide** | Service | Evaluates product composition to provide recycling instructions. |

---

## Professional Git Workflow
To ensure code quality and stability, the team adheres to the following workflow:

1. **Main Branch Protection:** The main branch is protected to ensure stability.
2. **Branching Strategy:** All development is performed on dedicated feature branches.
3. **Pull Requests:** Code is merged via pull requests only after the CI build and tests pass.
4. **Commit Standards:** Commits must be meaningful and clearly reflect the changes made.



