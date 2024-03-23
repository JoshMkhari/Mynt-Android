# Mynt - Coin Collection Management App

Mynt is an Android application designed to help users easily catalog their collection of South African coins. It implements modern design elements to provide a familiar and user-friendly experience. The application showcases a diverse set of skills and learnings in Android development, including:

## Android Fundamentals
- **Android Fragment Lifecycle Management**: Managing the lifecycle of fragments, which are modular UI components, through methods like `onCreateView()`.
- **Android UI Design and Layout Inflation**: Defining user interfaces using XML layout files and inflating them to bind views programmatically.
- **Event Handling and User Interaction**: Setting up event listeners and handling user interactions with various UI elements like buttons, spinners, and image pickers.
- **Camera Integration and Image Handling**: Integrating with the device's camera to capture images of coins and processing the captured images, such as storing them as byte arrays.

## Data Storage and Management
- **SQLite Database Integration**: Implementing an SQLite database to store coin and collection information, including creating tables, performing CRUD operations, and executing SQL queries.
- **Data Modeling**: Modeling and storing different types of data, such as coins, collections, users, and leaderboard entries, in the SQLite database.
- **Firebase Integration**: Integrating with Firebase services, including the Realtime Database for data synchronization and Storage for storing and retrieving coin images.

## Asynchronous Programming and Multithreading
- **Asynchronous Programming**: Utilizing asynchronous programming patterns, such as callbacks and listener interfaces, for Firebase database operations.
- **Multithreading**: Executing certain operations, like storing coins in the database and synchronizing with Firebase, on separate threads to prevent blocking the main UI thread.

## Data Retrieval and Manipulation
- **Data Parsing and Manipulation**: Parsing and processing retrieved coin data from Firebase to extract relevant information, such as mintage, observe, and reverse details.
- **Database Updates**: Updating the local SQLite database with data retrieved from Firebase, demonstrating the integration between cloud and local data storage.
- **Leaderboard Management**: Retrieving user data from Firebase and updating the local leaderboard based on user scores or points.

## General Software Development Skills
- **Code Organization and Modularization**: Organizing the codebase into separate classes and modules for better maintainability and reusability.
- **Asynchronous Programming**: Implementing asynchronous programming patterns to ensure smooth and responsive user experiences.
- **Multithreading**: Utilizing multithreading to offload time-consuming operations from the main UI thread, preventing UI freezes and ensuring responsiveness.
- **Data Persistence**: Implementing mechanisms for persisting data locally and synchronizing it with remote data sources, ensuring data availability and consistency across devices.
- **Integration with Third-Party Services**: Integrating with third-party services like Firebase for cloud storage, real-time database, and user authentication.
- **User Interface Design**: Designing user-friendly and intuitive interfaces that adhere to modern design principles and guidelines.

Overall, the Mynt application demonstrates proficiency in various aspects of Android development, including UI design, event handling, camera integration, database management, cloud integration, asynchronous programming, and multithreading. The provided code samples showcase the ability to develop complex mobile applications with modern features and cloud integration.
