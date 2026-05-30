# Group Project - Design and Architecture
**By:** Pavlos Antoniou, Layth Al Mardini, Mohammad Rabie Khoja  
**May 2026**

---

## Part 1: Domain Model & Responsibilities

### 1.1 Key Use Cases
Four primary options dictate the system workflow: Register New Material, Register New Product, Swap Environmental Strategy option, and Generate an Impact Report. The user can register new materials or use existing ones in order to register a product made up of these materials. Switching the strategy means that the calculation formula can be switched dynamically. Reports evaluate environmental levels based on the active formula.

### 1.2 Class Responsibilities
Responsibilities are assigned using the Information Expert principle: operations occur where the data lives.

* **Product (Entity):** Tracks material compositions, category classification, and lifespan.
* **Material (Entity):** Tracks recyclability score and recyclability text guidelines.
* **ImpactStrategy (Interface):** Defines the contract for calculation algorithms.
* **SimpleSumStrategy / WeightedStrategy (Concrete):** Calculate linear footprints or formulas scaled by lifespan.
* **ProductService (Service):** Coordinates use cases, and save products to repository.
* **InMemory Product Repository (Utility):** Maintains the in memory collection for product tracking.

### 1.3 Comparison of Alternative Designs
Alternatively, we could get rid of the strategy pattern and hard code the formulas in conditional blocks inside Product. This would cut down the class sizes at the start. It was rejected because it violates the Single Responsibility Principle (SRP) and increases coupling. This will ensure maintainability, isolation and seamless extension if we decouple the calculations in an independent hierarchy.

---

## Part 2: Design Principles, Patterns & Architecture

### 2.1 Correction of Design Principle Violation
The initial prototype of the system contained hard-coded console parsing, data sorting, mathematical formulas, and string formatting directly in the use-case service layer. This violation of the Single Responsibility Principle occurred because the service had different axes of change.

#### 2.1.1 Problem Analysis and Solution to Redesign
We combined UI stream logic with mathematical tracking algorithms, so formatting changes broke processing loops. The refactored solution separates these concerns: Menus in the Presentation Layer only control terminal processing and parsing; Application Services work with pure transaction workflows and calculations with domain entities. Now each system class has one and only one reason to change its source code.

### 2.2 Design Pattern: Strategy Pattern Implementation
The Strategy pattern is applied to environmental calculations to support multiple interchangeable algorithms. Without this pattern, the domain would rely on nested conditional statements. By making the context layer depend strictly on the abstract ImpactStrategy interface, new regulatory formulas can be integrated as independent subclasses without modifying core models, which would break the open for extension laws. Toggling strategies happens safely at runtime through direct menu options.

#### 2.2.1 Mathematical Formulas

**SimpleSumStrategy:** $$PCF_{raw}=\sum_{i=1}^{n}(m_{i}\times EF_{i})$$

**WeightedStrategy:** $$PCF_{final}=(\frac{\sum_{i=1}^{n}(m_{i}\times EF_{i})}{L})\times(1-\sum_{i=1}^{n}(\frac{m_{i}}{M}\times R_{i}\times\delta))$$

**Variables:**
* $m_{i} =$ material mass
* $EF_{i} =$ emission factor
* $L =$ lifespan
* $M =$ total mass
* $R_{i} =$ recyclability
* $\delta =$ credit factor (0.15)

### 2.3 Three-Layer Architecture & Dependency Inversion
The architecture enforces a strict three-tier layout (presentation, application, domain) to manage dependency streams. The domain maintains zero knowledge of outer frameworks. Following the Dependency Inversion Principle (DIP), major repository and calculation interfaces reside directly in the Domain Layer. The domain dictates what it requires, and outer application utilities fulfill that contract. We apply Constructor Injection within Main.java to hand information to application and presentation layers, keeping the domain layer code completely isolated.

---

## Part 3: Technical Debt Evaluation

### 3.1 Identified Technical Debt
To meet early iteration deadlines, specific areas of technical debt were intentionally accepted:

* **Volatile In-Memory Repositories:** Using collections instead of database frameworks like SQL skips external setup overhead but makes all stored tracking entities weak and hard to extend across standard runtime applications.
* **Console IO Binding:** The console UI is tightly coupled to the system which means a shift to a web interface would require a lot of extra work.

### 3.2 Architectural Mitigation Strategy
Because our system strictly adheres to the Dependency Inversion Principle, our storage debt is isolated. Services depend entirely on abstract domain repositories. We can drop in a permanent database provider later by creating a concrete data mapper without altering any domain business code.
