# 🏆 PredictionCup

PredictionCup is a web application for predicting match results, featuring a scoring system and player rankings.
Users can predict match outcomes, earn points, and compete in the ranking system.
Administrators have the ability to add, menage games.

---

## Features

### User Features

- Registration via email and password,
- Login with username,
- Password change functionality,
- Browse available games (SCHEDULED and FINISHED status),
- Predict game results (before the match starts),
- Earn points after game completion:
    - **3 points** - exact score prediction,
    - **1 point** - correct winner/draw prediction with wrong score,
    - **0 points** - incorrect prediction,

- View ranking with information about current position and position changes compared to previous game,
- View other compatitors prediction and score for each finished game;


---

### Admin Features
- Dodawanie nowych meczów.
- Edytowanie istniejących meczów.
- Usuwanie meczów (jeśli nie rozpoczęte lub przy spełnieniu warunków).
- Oznaczanie meczu jako **zakończony** — uruchamia naliczanie punktów dla wszystkich użytkowników.
- Add new game,
- Edit existing games,
- Delete games only with ADMIN_VIEW status,
- Mark matches as finished - triggers point calculation for all users,
- Admin-only game view (regular users only see scheduled and finished games),
---

## Technology Stack

- **Backend**:
    - Spring Boot
    - Spring Data JPA
    - Spring Security
    - Spring Validation
    - Lombok
    - MySQL
    - OpenAPI (Swagger UI)

- **Frontend**:
    - Thymeleaf
    - Bootstrap 5
    - thymeleaf-extras-springsecurity

---

## Application Structure

- `/` – Strona główna (lista meczów, możliwość typowania)
- `/login` – Logowanie
- `/register` – Rejestracja
- `/ranking` – Ranking użytkowników
- `/admin/matches` – Panel zarządzania meczami (ADMIN)
- `/users/{id}/password` – Zmiana hasła użytkownika
- Home page (match list, prediction functionality)

/login - Login page

/register - Registration page

/ranking - User rankings

/admin/matches - Match management panel (ADMIN only)

/users/{id}/password - Password change functionality

---

## Requirements

- Java 17+
- Maven 3+
- MySQL 8+
- Web browser

---

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/twoje-repo/PredictionCup.git
   cd PredictionCup

2. **Configure database**

- Create MySQL database
- Update application.properties with your database credentials

3. **Build and run the application**


```bash
mvn clean install
mvn spring-boot:run
```

4.**Access the application**

- Main application: http://localhost:8080

- Swagger API documentation: http://localhost:8080/swagger-ui.html


## Key Functionality

### Match Management
CRUD operations for matches completed

Date validation ensures matches are properly scheduled/finished

Match status changes trigger automatic point calculation

Admin view - exclusive access for administrators, regular users see only scheduled matches

### Ranking System

Stateless ranking - updates automatically with each GET request

Ranking history - tracks user position changes, promotions and demotions

Real-time ranking updates when matches are finished

### Match Status
Each match must have a status at every stage

Validation prevents adding results without finishing the match

### API Features
POST endpoints accept partial requests without overwriting fields with null values


## Roadmap
- Enhenced dashboard with statistics

