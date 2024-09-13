# My Plants

My Plants helps you remember to water your plants, ensuring they receive the care they need to thrive. Developed using Kotlin Multiplatform Mobile (KMM), it currently supports Android with an iOS version in progress. Future updates will include a backend to enable online data storage and syncing.

---

List Screen | Detail Screen | Edit Screen
:-------------------------:|:-------------------------: |:-------------------------:
 ![Screenshot_20240913-180438](https://github.com/user-attachments/assets/0e7bb858-13cf-4bbf-910e-06002c03b123) | ![Screenshot_20240913-180433](https://github.com/user-attachments/assets/b64c0dcf-b735-4804-be46-3090c9aa3468) | ![Screenshot_20240913-180444](https://github.com/user-attachments/assets/6bb3e9d1-3cd9-4390-af18-d4fbab61dc6d) 
---
# Tech used:

Kotlin:  
  •Coroutines: For asynchronous operations and smooth multitasking.  
  •Flows & StateFlows: To manage and observe state changes in a reactive way.  
  •Lambda Functions & Higher-Order Functions: For functional programming paradigms and cleaner code.  
  •Sealed Classes: To model complex state and event hierarchies.  
  •Channels & Mutex: For concurrent programming and managing thread safety.  
  •Kotlinx-Serialization: To handle JSON serialization and deserialization.  

Android:  
  •WorkManager: For handling scheduled tasks and background work.  
  •Alarm Manager: To set up alarms for watering reminders.  
  •Intents: For communication between different app components.  
  •Jetpack Compose: For modern, declarative UI development.  
  •Baseline Profiles: To optimize app performance by reducing method count.  
  •JUnit: For unit testing to ensure code quality.  
  
Libraries:  
  •Coil: For efficient image loading and caching.  
  •Koin: For dependency injection.  
  •Ktlint: For Kotlin code formatting and linting.  
  •SQLDelight: For type-safe SQL database access.  

Multiplatform:  
  •Kotlin Multiplatform Mobile (KMM): For shared code across Android and iOS platforms.  
  •MOKO Resources & MOKO ViewModels: For managing resources and view models in a multiplatform context.  

Architecture:  
  •MVVM (Model-View-ViewModel): For a clean separation of concerns and maintainable code.  
