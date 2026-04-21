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
## Architectural Rationale

## a) Layer Allocations 

This system implements a three-layer model to ensure a strict separation of responsibilities. Each class is assigned to a specific package based on its role in the project:  

- **Domain Layer (domain/)**: This is the foundation of the project that contains entities such as `Product` and `Material`. It encapsulates the core business and environmental rules while remaining isolated from other parts such as the UI and storage processes.  

- **Application Layer (application/)**: This layer contains all our use-case classes, such as `ProductService` and `MaterialService`. Furthermore, it also holds the concrete implementations that are used for storing products or materials, such as `InMemoryProductRepository`. All classes that involve any computing, whether it's storing, listing, or calculating, will be in this layer.  

- **Presentation Layer (presentation/)**: This package handles all user interaction: what they see and what they can input. It contains the `ConsoleMenu` class, which has the responsibility of capturing user input via the `Scanner` and formatting an output. This layer contains no business logic at all.  



## b) Interface Placements 
 
Major interfaces such as `ProductRepository` and `ImpactStrategy` were placed directly in the Domain layer. This decision follows the **Dependency Inversion Principle (DIP)**: 

- **Ownership**: The domain layer “owns” the interface. It defines what it needs from a storage class or a calculation strategy class without being tied to a specific implementation.  

- **Decoupling**: By placing the interface in the domain, the service classes depend only on abstractions. This allows all the implementation of `InMemoryProductRepository` (in the application layer) to be altered without any changes being made to the domain entities.  




## c) Dependency Direction 
 
This system ensures that an **Inward Dependency Rule** is followed: 

- **One-way flow**: Dependencies point only inward towards higher-level layers, which means that the Presentation layer depends on the Application layer, and the Application layer depends on the Domain layer.  

- **Isolation**: The domain layer does not import anything from outer layers, which keeps it stable.  

- **Maintenance through injection**: The direction is maintained by using **Constructor Injection** in `Main`, which means that it instantiates the concrete repository and injects it into the service. This leads to the service not knowing about the concrete storage class, only the interface in the domain layer.  

---