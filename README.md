# **Student Project Management Portal**

A comprehensive backend system for managing student projects, applications, and communication between students and faculty. Built with Spring Boot and MySQL.

## 📋 Table of Contents
- [Features](#features)
- [Technology Stack](#technology-stack)
- [System Architecture](#system-architecture)
- [Database Schema](#database-schema)
- [API Documentation](#api-documentation)
- [Setup Instructions](#setup-instructions)
- [Project Structure](#project-structure)
- [Usage Examples](#usage-examples)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### 👥 User Management
- **Role-based authentication** (Student/Faculty/Admin)
- User registration with role-specific data
- Secure login system
- Admin dashboard with user management

### 📚 Project Management
- Faculty can create, update, and delete projects
- Project capacity management
- Deadline tracking
- Advanced search functionality
- Duplicate project prevention

### 📝 Application System
- Students can apply to projects
- Application status tracking (Pending/Approved/Rejected)
- Limits: Max 5 applications per student
- Project capacity validation
- Application withdrawal capability

### 💬 Messaging System
- In-app messaging between faculty and students
- Application-specific conversations
- Automated notifications on status changes
- Inbox for users

### 👨‍💼 Administrative Features
- Comprehensive dashboard with statistics
- User, project, and application management
- System monitoring capabilities

## 🛠 Technology Stack

### Backend
- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA** (with Hibernate)
- **Spring Web** (REST API)
- **Jakarta Persistence**

### Database
- **MySQL 8+**
- **Flyway** (Database migration)
- **JDBC** connectivity

### Development Tools
- **Maven** (Dependency management)
- **Spring Boot DevTools**
- **Lombok** (Recommended for future)

## 🏗 System Architecture

```
Client (Frontend/Postman)
    ↓
REST Controllers (API Layer)
    ↓
Service Layer (Business Logic)
    ↓
Repository Layer (Data Access)
    ↓
Database (MySQL)
```

### Data Flow:
1. **HTTP Request** → Controller
2. **Controller** → Service (Business Logic)
3. **Service** → Repository (Database Operations)
4. **Repository** → Database
5. **Response** → Client

## 🗄 Database Schema

### Core Tables:
1. **`users`** - User authentication and basic info
2. **`student`** - Student-specific details (branch, year)
3. **`faculty`** - Faculty-specific details (department)
4. **`project`** - Project information and deadlines
5. **`application`** - Student applications to projects
6. **`messages`** - Communication between users

### Relationships:
- One User → One Student/Faculty
- One Faculty → Many Projects
- Many Students ↔ Many Projects (through Applications)
- One Application → Many Messages

## 📚 API Documentation

### Base URL: `http://localhost:8080/api`

### Authentication Endpoints (`/api/auth`)
| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/register` | Register new user | `RegisterRequest` |
| POST | `/login` | User login | `LoginRequest` |

### Project Endpoints (`/api/projects`)
| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| POST | `/` | Create new project | `ProjectDto` |
| GET | `/` | Get all projects | None |
| GET | `/faculty/{id}` | Get projects by faculty | Path variable |
| DELETE | `/{id}` | Delete project | Path variable |
| PUT | `/{id}/details` | Update project details | title, description |
| PATCH | `/{id}/deadline` | Update deadline | deadline |
| PATCH | `/{id}/capacity` | Update capacity | capacity |
| GET | `/search` | Search projects | facultyId, title, deadline |

### Application Endpoints (`/api/applications`)
| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| POST | `/` | Apply to project | `ApplicationDto` |
| GET | `/student/{id}` | Get student's applications | Path variable |
| GET | `/project/{id}` | Get project applications | Path variable |
| PUT | `/{id}/status` | Update status | status |
| GET | `/faculty/{id}` | Get faculty's applications | Path variable |
| GET | `/project/{id}/students` | Get applicants for project | Path variable |
| DELETE | `/{id}` | Delete application | Path variable |
| DELETE | `/{id}/student/{sid}/withdraw` | Withdraw application | Path variables |

### Message Endpoints (`/api/messages`)
| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| POST | `/` | Send message | applicationId, senderId, receiverId, content |
| GET | `/application/{id}` | Get messages for application | Path variable |
| GET | `/inbox/{id}` | Get user's inbox | Path variable |

### Admin Endpoints (`/api/admin`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/users` | Get all users |
| DELETE | `/users/{id}` | Delete user |
| GET | `/projects` | Get all projects |
| DELETE | `/projects/{id}` | Delete project |
| GET | `/applications` | Get all applications |
| GET | `/count` | Get dashboard counts |

## 🚀 Setup Instructions

### Prerequisites
1. **Java 17+** installed
2. **MySQL 8+** installed and running
3. **Maven** installed
4. **Git** installed

### Step 1: Clone the Repository
```bash
git clone https://github.com/yourusername/student-project-portal.git
cd student-project-portal
```

### Step 2: Configure Database
1. Create MySQL database:
```sql
CREATE DATABASE projectportal;
CREATE USER 'portal_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON projectportal.* TO 'portal_user'@'localhost';
FLUSH PRIVILEGES;
```

2. Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/projectportal
spring.datasource.username=portal_user
spring.datasource.password=your_password
```

### Step 3: Build the Project
```bash
mvn clean install
```

### Step 4: Run the Application
```bash
mvn spring-boot:run
```

Or run the main class:
```bash
java -jar target/projectportal-0.0.1-SNAPSHOT.jar
```

### Step 5: Verify Installation
Access the API at: `http://localhost:8080`
Check database tables are created via Flyway migration.

## 📁 Project Structure

```
src/main/java/com/example/projectportal/
├── config/           # Configuration classes
├── controller/       # REST Controllers
│   ├── AuthController.java
│   ├── ProjectController.java
│   ├── ApplicationController.java
│   ├── MessageController.java
│   └── AdminController.java
├── dto/              # Data Transfer Objects
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   ├── ProjectDto.java
│   └── ApplicationDto.java
├── model/            # Entity classes
│   ├── User.java
│   ├── Student.java
│   ├── Faculty.java
│   ├── Project.java
│   ├── Application.java
│   └── Message.java
├── repository/       # Data access layer
│   ├── UserRepository.java
│   ├── StudentRepository.java
│   ├── FacultyRepository.java
│   ├── ProjectRepository.java
│   ├── ApplicationRepository.java
│   └── MessageRepository.java
├── service/          # Business logic layer
│   ├── AuthService.java (interface)
│   ├── AuthServiceImpl.java
│   ├── ProjectService.java (interface)
│   ├── ProjectServiceImpl.java
│   ├── ApplicationService.java (interface)
│   ├── ApplicationServiceImpl.java
│   ├── MessageService.java (interface)
│   └── MessageServiceImpl.java
└── ProjectPortalApplication.java  # Main application class

src/main/resources/
├── application.properties         # Configuration
└── db/migration/
    └── V1__initial_schema.sql    # Database schema
```

## 📖 Usage Examples

### 1. User Registration (Student)
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@student.edu",
    "password": "password123",
    "role": "STUDENT",
    "branch": "Computer Science",
    "year": "Third Year"
  }'
```

### 2. User Registration (Faculty)
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Dr. Smith",
    "email": "smith@faculty.edu",
    "password": "password123",
    "role": "FACULTY",
    "department": "Computer Science"
  }'
```

### 3. Create a Project (Faculty)
```bash
curl -X POST http://localhost:8080/api/projects \
  -H "Content-Type: application/json" \
  -d '{
    "title": "AI Research Project",
    "description": "Research on machine learning algorithms",
    "deadline": "2024-12-31",
    "facultyId": 1,
    "role": "FACULTY",
    "projectCapacity": 5
  }'
```

### 4. Apply to Project (Student)
```bash
curl -X POST http://localhost:8080/api/applications \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "projectId": 1,
    "role": "student"
  }'
```

### 5. Update Application Status (Faculty)
```bash
curl -X PUT "http://localhost:8080/api/applications/1/status?status=APPROVED"
```

### 6. Send Message
```bash
curl -X POST "http://localhost:8080/api/messages?applicationId=1&senderId=2&receiverId=1&content=Your application has been reviewed"
```

## 🧪 Testing

### Using Postman
1. Import the Postman collection (available in `/docs` folder)
2. Set environment variables for base URL
3. Test endpoints in sequence: Register → Login → Projects → Applications

### Unit Testing (To be implemented)
```bash
mvn test
```

## 🔧 Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/projectportal
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

# Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

### Environment Variables
Set environment variables for production:
```bash
export DB_URL=jdbc:mysql://localhost:3306/projectportal
export DB_USERNAME=portal_user
export DB_PASSWORD=secure_password
```

## 📊 Sample Data

### Initial Admin User
You can create an admin user by running:
```sql
INSERT INTO users (name, email, password, role) 
VALUES ('Admin', 'admin@portal.edu', 'admin123', 'FACULTY');
```

### Sample Projects
```sql
INSERT INTO project (title, description, faculty_id, deadline, project_capacity)
VALUES 
('Web Development', 'Build a full-stack web application', 1, '2024-06-30', 3),
('Data Analysis', 'Analyze datasets using Python', 1, '2024-07-15', 4),
('Mobile App', 'Develop cross-platform mobile application', 1, '2024-08-31', 2);
```

## 🚨 Error Handling

The system returns appropriate HTTP status codes:

- **200**: Success
- **400**: Bad Request (validation errors)
- **401**: Unauthorized (authentication failed)
- **403**: Forbidden (insufficient permissions)
- **404**: Not Found (resource doesn't exist)
- **409**: Conflict (duplicate data)
- **500**: Internal Server Error

## 📈 Future Enhancements

### High Priority
1. **Password encryption** (BCrypt)
2. **JWT authentication** for stateless sessions
3. **Email notifications** for important events
4. **File uploads** for project documents

### Medium Priority
1. **Pagination** for list endpoints
2. **Advanced search** with filters
3. **Export functionality** (PDF/Excel reports)
4. **Real-time notifications** (WebSockets)
5. **Project progress tracking**

### Low Priority
1. **Calendar integration** for deadlines
2. **Team formation** for group projects
3. **Peer reviews** and ratings
4. **Analytics dashboard** with charts
5. **Mobile app** (React Native/Flutter)

## 🤝 Contributing

### Development Workflow
1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit changes: `git commit -m 'Add feature'`
4. Push to branch: `git push origin feature-name`
5. Submit a Pull Request

### Code Style Guidelines
- Follow Java naming conventions
- Use meaningful variable and method names
- Add comments for complex logic
- Write unit tests for new features
- Update documentation accordingly

## 📞 Support

For support, please:
1. Check the [Wiki](https://github.com/yourusername/student-project-portal/wiki)
2. Search existing [Issues](https://github.com/yourusername/student-project-portal/issues)
3. Create a new issue with detailed description

## 🙏 Acknowledgments

- Built with [Spring Boot](https://spring.io/projects/spring-boot)
- Database management with [Flyway](https://flywaydb.org/)
- Inspired by university project management needs
