# LibraryApp

This application provides a convenient way to manage book rentals within a library system. With the ability to create, search, modify, and delete book records, employees can easily add new books to the system and keep track of existing ones. Similarly, library members can be created and managed in the system. When a book is rented, the employee can create a rental receipt, which includes information about the book, the borrowing library member, the library building, and the rental date. When rented books are returned, the rental confirmation can be updated to reflect their return to the library.

## Implementation

The application was developed using the Java programming language, with the Java Swing used to implement the graphical user interface. Communication between the server and client sides of the application is established via a TCP connection. I created a connection pool to store connections to the database that are generated while the application is running. This pool ensures that system operations can access a free connection from the pool when needed, and return it to the pool once the operation is complete. For persistent data storage, the application relies on a MySQL database, which stores all relevant information.

## Software Architecture

The software system is built on a three-level architecture, comprising the following components:

- User interface
- Application logic
  - Application logic controller
  - Business logic (structure and behavior)
  - Database broker
- Database

The user interface is located on the client-side, while the application logic and data storage components are on the server-side of the application.

![Software architecture](https://github.com/djordjije11/LibraryApp/blob/main/images/Software%20architecture.png?raw=true "Software architecture")

## How to start an app

To start the server side, you need to run the Server class, which will display the user interface. From this interface, you can start the application server, view a list of currently logged-in users, and make any necessary configuration changes.
On the client side, start the application by running the Client class. This will open a login screen, where users can enter their ID and password to access the system. Once logged in, users can execute any of the existing system functionalities as needed.

![client-screen](https://github.com/djordjije11/LibraryApp/blob/main/images/client-screen.png?raw=true "client-screen")

### Detailed documentation in Serbian language
[Link ka dokumentaciji](https://github.com/djordjije11/LibraryApp/blob/main/dokumentacija/2019-0162-%D0%82%D0%BE%D1%80%D1%92%D0%B8%D1%98%D0%B5-%D0%A0%D0%B0%D0%B4%D0%BE%D0%B2%D0%B8%D1%9B.pdf)
