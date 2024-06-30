
# Roman Numeral Rest App

---

## How do I get setup?

### Before I start
- Java 17 installed
  - <https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html>
- Maven installed
  - <https://maven.apache.org/what-is-maven.html>
- Make installed (**Optional**)
  - macos: <https://formulae.brew.sh/formula/make>
  - windows: ...
  - linux: ...
- Dependencies Installed
```bash
mvn install
```

---

## How do I run the app?

- See `Makefile` or below commands

### Run Commands

- Run via Make
```bash
make run
```

- Alternatively Run via maven command
```bash
mvn spring-boot:run
```

---

## How do I test the app?

### Test Commands

- Run make
```bash
make test
```

- Alternatively via maven command
```bash
mvn test
```

---
## Generate Test Report
```bash
make test
make generate-report
make open-report
```

---

## Where do I access the app locally?
- *Ensure app is running*
- http://localhost:8080/romannumeral?query=2200000000

```bash
make start
```

---

## Engineering Methodology

 All contributions are expected to undergo thorough code reviews to maintain code quality 
and consistency. All new contributions should contain test coverage and explanations of new code in commits/PRs.

### Design Principles
We adhere to SOLID principles to ensure our codebase remains robust, maintainable, and scalable. We strive to avoid
redundancy by following the DRY principle across all codebases.

### Architectural Overview
We utilize a microservices architecture, facilitating scalable and independent service management. Our RESTful APIs 
are designed to be stateless and are documented using Swagger for clarity and ease of use.

### Technologies Used
- **Spring Web MVC**: For rapid API development.
- **Junit Test FrameWork**: for extensive unit testing.

---

## Testing Methodology

Our testing framework is comprehensive, ensuring high reliability and fewer regressions in our codebase.

### Testing Types
- **Unit Testing**: We use JUnit and Mockito for unit tests, aiming to cover both positive, negative and edge case 
scenarios for all methods. 
- **Description**: We these test individual components in isolation, ensuring that each part functions correctly independently.


### Test Coverage
We aim for at least **80%** test coverage.

### CI/CD
**CI/CD** will run test suites on every **pushed commit**
and **pull request**, ensuring that only tested and stable code is merged and deployed to production. Packages
should be regularly updated and CVEs scanned and mitigated promptly.

---


## Package Layout
```text
./src/main/java

com.chase.app.roman
├── config            // Classes for configuration settings
├── controllers       // Web layer /REST API controllers
├── service           // Business logic and service layer
├── dal               // Data access layer, typically interfaces for database access
├── dto               // Data Transfer Objects
├── exceptions        // Custom exceptions and error handling classes
└── Application.java  // Main application class
```

---

## Dependency Attribution

This project utilizes several key technologies and libraries. Below are the main dependencies along with their 
descriptions and licensing information:

**Spring Framework**: Spring Boot simplifies the development of new Spring applications through convention 
over configuration. It enables easy setup and development of stand-alone, production-grade 
Spring based applications. For more details see [official page](https://spring.io/guides/gs/spring-boot/).

- **License**: [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

**JUnit**: Unit tests in JUnit Jupiter, enhancing testing capabilities. 
More on [JUnit 5's Offical Page](https://junit.org/junit5/)

- **License**:  [Eclipse Public License 1.0](http://www.eclipse.org/legal/epl-v10.html).


**Caffeine on Github**:  by Ben Manes as performance caching library in our project. 
see more on [Caffeine on GitHub](https://github.com/ben-manes/caffeine)
- **License**: [Apache License 2.0](https://github.com/ben-manes/caffeine/blob/master/LICENSE)

---

## Documentation References

### Roman Numerals Ref #1:
- [Wikipedia Roman-Numerals reference](https://en.wikipedia.org/wiki/Roman_numerals)

### Roman Numerals Ref #2:
- [Roman Numeral - FreeCodeCamp Article](https://www.freecodecamp.org/news/roman-numerals-the-roman-numeral-for-4-6-9-and-others/)

### Roman Numerals Ref #3:
- [YoutubeVideo](https://www.youtube.com/watch?v=301M30jCugY)

### Spring Web MVC:
- [Spring MVC Web Docs](https://docs.spring.io/spring-framework/reference/web/webmvc.html)

---

Roman Numerals - Vinculum Use **Clarification**:

- **Vinculum**: is applied after each character with a single "\u0305" or single "\u033F" after 
to apply the single overline or double overline respectively.
- **For example**: M + "\u033F" + "M" + "\u033F"
  - M̿M̿ = 2,000,000,000
  - Above we have A MM with double overline indicating 2 billion
- Use of Vinculum
  - A single overline indicates multiplication by 1 thousand (10^3) - (\u0305)
  - A double overline indicates a multiplication by 1 million (10^6) - (\u033F)
---

## Who do I talk to?

### Contributors
- Chase Wilcox <chasebwilcox@gmail.com>
