# RadiantHub

RadiantHub is an Android application developed in Kotlin with Jetpack Compose, designed to explore agents, abilities, maps, and other content from Valorant.

I built this project to practice API integration, local persistence, state management, and modern Android UI development while exploring a more immersive visual experience.

- [Technologies](#technologies-used)  
- [Features](#features)  
- [Preview](#preview)  
- [Technical Decisions](#technical-decisions)   
- [Project Goal](#project-goal)  
- [Contact](#contact)  
- [Disclaimer](#disclaimer)

## Technologies used

- [Kotlin](https://kotlinlang.org/) — Main development language (my favorite 💜)
- [Jetpack Compose](https://developer.android.com/jetpack/compose) — Declarative UI toolkit for building modern Android interfaces
- [Material 3](https://m3.material.io/) — Used to maintain visual consistency and modern UI practices
- [Retrofit](https://square.github.io/retrofit/) — HTTP client for REST API consumption
- [Koin](https://insert-koin.io/) — Dependency Injection for simpler and cleaner dependency management
- [Coil](https://coil-kt.github.io/coil/compose/) — Asynchronous image loading via URL
- [Navigation](https://developer.android.com/jetpack/compose/navigation) — Navigation management between screens
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) — User preference persistence
- MVVM Architecture — Project organization using ViewModel and StateFlow for state management and separation of responsibilities
- Text management with `strings.xml` — Centralized text management for easier maintenance and future localization

## Features

### Agents
* Browse playable Valorant agents  
* Immersive fullscreen agent presentation:
  * Blurred cinematic background  
  * Centered agent artwork  
* Agent details:
  * Name  
  * Role  
  * Description  
* Smooth horizontal navigation between agents using `HorizontalPager`

### Agent Dossier
* BottomSheet with detailed agent abilities
* Ability list including:
  * Icon  
  * Ability name  
  * Description

### Maps
* Browse Valorant maps
* Fullscreen map presentation
* Smooth horizontal navigation between maps using `HorizontalPager`
* Map details
  * Name
  * Visual map overview

### Navigation & UI
* TopBar and BottomBar navigation
* Navigation Drawer with:
  * App routes  
  * Theme preferences  
* Theme support:
  * `Dark`
  * `Light`
  * `System`
* Synchronization with:
  * Status Bar  
  * Navigation Bar

### General
* Handling of loading and error states with retry option
* Public API integration with Valorant API
* Dynamic UI rendering using Compose State

## Preview
### Agents Screen and Agent Abilities
<img src="https://github.com/user-attachments/assets/1b8e88ff-fce6-49ba-b115-fd0c6ea13239" width="250"/>
<img src="https://github.com/user-attachments/assets/d841cfac-046f-4fc8-a82f-d0f03451bc39" width="250"/>

### Maps Screen
<img src="https://github.com/user-attachments/assets/bbb0f532-370b-454c-bc2a-0b3b59049e0d" width="250"/>

Below I will provide a video showing how the application looks:  
> https://github.com/user-attachments/assets/02e5ba1c-529a-4e1c-93fe-f9107b38362e

## Technical Decisions

### Jetpack Compose
I chose Jetpack Compose because I already had experience with the declarative approach, which makes UI construction more organized. I honestly don't miss XML ;)

### Architecture (MVVM)
I chose to structure the project using MVVM to keep the logic separated from the UI.  
The use of ViewModel with StateFlow allows the UI to automatically react to state changes.  

> The project follows a feature-based organization, keeping UI, domain, and data responsibilities separated to reduce coupling and improve scalability.

### API Consumption
The application consumes data from the Valorant public API. This allowed me to practice HTTP requests, data handling, and layer organization within the project.

This allowed me to practice:
* REST API consumption  
* DTO mapping to domain models  
* Error handling  
* UI state management for loading, success, and error scenarios

> 🔗 API Link: https://valorant-api.com/

### UI State Management (UiState)
To handle screen states (loading, success, and error), I used an approach with sealed class (UiState) along with StateFlow.  
With this, the UI automatically reacts to state changes, making the code more organized and easier to understand.

### Image Loading
I used Coil to load remote images asynchronously, ensuring smooth rendering of agent portraits, role icons, and backgrounds.

### Material 3
Material 3 was chosen to maintain a modern and consistent visual design while taking advantage of well-structured components.

### Screen Navigation (Navigation)
To organize navigation between screens, I used Navigation Compose. This greatly simplifies route management and navigation flow.

## Project Goal

This project was developed with the goal of:

* Practicing REST API consumption  
* Improving UI development with Jetpack Compose  
* Improving UI/UX thinking  
* Improving state management using ViewModel and StateFlow  
* Building a stronger Android portfolio project

## Contact

🔗 [LinkedIn](https://www.linkedin.com/in/gblrodrigues/)

## Disclaimer

This project was developed exclusively for educational and portfolio purposes.

**RadiantHub** is not affiliated with, sponsored by, or endorsed by Riot Games.

The data used in this application is provided by public API:  
* Valorant API: https://valorant-api.com/
