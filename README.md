# miniCrm
# PUBLIC 
- To add these (Assignment)
  - Local setup instructions
  -  Architecture diagram --> https://excalidraw.com/ and add db diagrm 
  -  Summary of AI tools and other tech used
  -  Known limitations or assumptions

## Features

- Register, Get all customer pagnited, get a customer, update and delete a customer

- added oauth2 using google login
- saving user when login for the first time  

## OAuth2
- public class OAuthServiceImpl implements OAuth2UserService<R,U>
- R -> google object , U-> my entity 
- we cannot directly save details from oauth2 to our db

# Segment
- customer count-> fetch from db and count according to rule -> then return that count

# Rule Eval Service
- using JPQL , custom query to fetch customer as per the segment rule / condition

# Security util
- using security to get details about currently logged in admin from spring security



# STRUCTURE
## 1. Admin 
- Admin creates rules (>1000 spend or account older thant 1 year)
- These rulese are stored in segment

## 2. Segment
- Filter , find customers with these rules
- show number of customers for these conditions / rules

## 3. Campaign
- Message template
- Using segment it knows where to send mesages

# 4. Communication log 
- Record/ history
-  TODO completee later

  ## Admin (creates rules) → Segment (finds matching customers) → Campaign (sends message) → CommunicationLog (tracks delivery)



# miniCrm

## Local Setup Instructions

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven
- Google OAuth credentials

### Steps to Run
1. Clone the repository
```bash
git clone [repository-url]
cd miniCrm
```

2. Configure Database
- Create a MySQL database named `minicrm`
- Update `application.properties` with your database credentials

3. Configure Google OAuth
- Go to Google Cloud Console
- Create a new project
- Enable Google OAuth2 API
- Create OAuth 2.0 credentials
- Add authorized redirect URI: `http://localhost:8080/login/oauth2/code/google`
- Update `application.properties` with your OAuth credentials

4. Run the Application
```bash
mvn spring-boot:run
```

## Architecture Diagram

- Database Schema
- Application Flow
- Component Interaction

## Tech Stack & Tools

### Backend
- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- MySQL
- Maven

### Frontend
- React
- Material-UI
- Axios

### Development Tools
- IntelliJ IDEA
- Postman
- Git

### AI Tools Used
- GitHub Copilot for code suggestions
- ChatGPT for code review and documentation
- Claude for architecture design

## Known Limitations & Assumptions

### Current Limitations
1. Authentication
   - Only Google OAuth supported
   - Single session per user
   - No role-based access control

2. Campaign Management
   - Basic campaign tracking
   - Limited to email communications
   - No campaign scheduling

3. Customer Management
   - Basic customer data model
   - Limited customer interaction history
   - No customer feedback system

4. Technical Limitations
   - No caching implementation
   - Basic error handling
   - Limited scalability features

### Assumptions
1. Business Logic
   - One admin per organization
   - Campaigns are email-based
   - Customer data is manually entered

2. Technical
   - Single instance deployment
   - Local development environment
   - Standard business hours operation


## Features


## OAuth2


# Segment


# Rule Eval Service


# Security util


# STRUCTURE
