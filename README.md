# RadiantHub

RadiantHub is an Android application developed in Kotlin with Jetpack Compose, designed to explore agents, abilities, 
maps, and other content from Valorant.

I built this project to practice API integration, local persistence, state management, and modern Android UI 
development while exploring a more immersive visual experience.

- [Technologies](#technologies-used)  
- [Features](#features)  
- [Preview](#preview)  
- [Technical Decisions](#technical-decisions)   
- [Project Goal](#project-goal)  
- [Contact](#contact)  
- [Disclaimer](#disclaimer)

## Technologies used

| Category | Technology | Why |
|----------|------------|-----|
| Language | [![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/) | Main development language (my favorite 💜)
| UI | [![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose) | Declarative UI toolkit for building modern Android interfaces
| Design System | [![Material 3](https://img.shields.io/badge/Material%203-6750A4?style=for-the-badge&logo=materialdesign&logoColor=white)](https://m3.material.io/) | Used to maintain visual consistency and modern UI practices
| Networking | [![Retrofit](https://img.shields.io/badge/Retrofit-3DDC84?style=for-the-badge&logo=square&logoColor=white)](https://square.github.io/retrofit/) | HTTP client for REST API consumption
| DI | [![Koin](https://img.shields.io/badge/Koin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://insert-koin.io/) | Dependency Injection for simpler and cleaner dependency management
| Persistence | [![Room](https://img.shields.io/badge/Room-4285F4?style=for-the-badge&logo=sqlite&logoColor=white)](https://developer.android.com/training/data-storage/room) | Local persistence for favorites, including migration handling and state restoration
| Preferences | [![DataStore](https://img.shields.io/badge/DataStore-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/topic/libraries/architecture/datastore) | User preference persistence
| Image Loading | [![Coil](https://img.shields.io/badge/Coil-FF6F00?style=for-the-badge&logo=kotlin&logoColor=white)](https://coil-kt.github.io/coil/compose/) | Asynchronous image loading via URL
| Navigation | [![Navigation Compose](https://img.shields.io/badge/Navigation%20Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/jetpack/compose/navigation)  | Navigation management between screens
| Localization | strings.xml | Centralized text management for easier maintenance and future localization
| Architecture | MVVM + StateFlow | Project organization using ViewModel and StateFlow for state management and separation of responsibilities

## Features

### Home
* Quick access to:
  * Agents
  * Maps
  * Cards
  * Competitive Tiers
  * Favorites
* Themed banners for each section

### Agents
* Browse playable Valorant agents
* Carousel position indicator
* Immersive fullscreen agent presentation:
  * Blurred cinematic background  
  * Centered agent artwork  
* Agent details:
  * Name  
  * Role  
  * Description  

### Agent Details
* BottomSheet with detailed agent information
  * Icon
  * Agent name
  * Description
* Ability list including:
  * Icon
  * Ability name
  * Description

### Maps
* Browse all Valorant maps
* Carousel position indicator
* Fullscreen tactical map visualization
* Detailed map information
  * Coordinates
  * Callout count
  * Region count
* Interactive callouts explorer
  * Grouped by tactical regions
  * Quick access through bottom sheet
 
### Player Cards
* Browse Valorant player cards
* Grid-based cards gallery
* Favorite player cards

### Competitive Tiers
* Browse all Valorant competitive tiers
* Organized by rank divisions
* Tier icons and progression visualization
* Search integration with automatic navigation

### Favorites
* Filtering by All, Agents and Cards categories
* Persistent local favorites system for agents and player cards
* Agent favorites with immersive background and portrait presentation
* Card favorites using dedicated wide artwork
* Undo support when removing favorites
* Favorite restoration to original position
* Dynamic favorite toggling directly from agent pages

### Search
* Global search across agents, maps, player cards, and competitive tiers
* Real time filtering while typing
* Automatic navigation from search results
* Automatic scrolling and visual highlighting for searched player cards and competitive tiers

### Navigation & UI
* TopBar and BottomBar navigation
* Navigation Drawer with:
  * App routes
  * Language preferences
  * Theme preferences  
* Theme support:
  * `Dark`
  * `Light`
  * `System`
* Language support:
  * `English`
  * `Português (Brasil)`
  * `Español`
* Dynamic API localization based on selected language
* Synchronization with:
  * Status Bar  
  * Navigation Bar

### General
* Handling of loading and error states with retry option
* Network connectivity monitoring
* Offline and connection-restored feedback
* Automatic retry of failed requests when connectivity is restored
* Public API integration with Valorant API
* Practicing application localization and internationalization (i18n)
* Dynamic UI rendering using Compose State
* Runtime language switching
* Persistent language preferences
* API content localization

## Preview
### Home Screen
<img src="https://github.com/user-attachments/assets/a8446100-6236-4d78-a553-858aa50cf815" width="250"/>

### Agents Screen and Agent Details
<img src="https://github.com/user-attachments/assets/53043637-bff7-4b2c-801b-1f53aea9543e" width="250"/>
<img src="https://github.com/user-attachments/assets/adedda93-c83c-433c-8715-faecb35f006e" width="250"/>

### Maps Screen
<img src="https://github.com/user-attachments/assets/873aa49b-ec7a-4fd1-9bc9-01ad7efd1a2b" width="250"/>
<img src="https://github.com/user-attachments/assets/4880c099-7ed9-4fb1-87f6-3582c3a9e383" width="250"/>

### Cards Screen
<img src="https://github.com/user-attachments/assets/9b3e04a1-6ab8-4005-a5bb-aa2d53bbb1ab" width="250"/>

### Tiers Screen
<img src="https://github.com/user-attachments/assets/badcac2a-f1b2-460e-9c15-25e155949921" width="250"/>

### Favorites Screen
<img src="https://github.com/user-attachments/assets/bbf2ad2c-6e2c-488c-bc5f-8a27479dfb74" width="250"/>
<img src="https://github.com/user-attachments/assets/66c7ea9b-3bbc-43a9-9f0f-fbfdd8cc942b" width="250"/>
<img src="https://github.com/user-attachments/assets/15479e8f-3a71-40df-972a-a94aa6328047" width="250"/>

### Search 
<img src="https://github.com/user-attachments/assets/d0fbf87e-c3ea-44e7-91fa-53739385145b" width="250"/>
<img src="https://github.com/user-attachments/assets/71835c39-ea68-4b73-bc62-4c646297e8c8" width="250"/>

Below I will provide a video showing how the application looks:  
> https://github.com/user-attachments/assets/c7cc0441-8cc8-4455-a632-f9ffaaee480f

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

### Data Persistence (Room + DataStore)

I implemented Room for local persistence of favorite agents and DataStore for managing user personalization preferences such as theme and language selection.

### UI State Management (UiState)
To handle screen states (loading, success, and error), I used an approach with sealed class (UiState) along with StateFlow.  
With this, the UI automatically reacts to state changes, making the code more organized and easier to understand.

### Image Loading
I used Coil to load remote images asynchronously, ensuring smooth rendering of agent portraits, role icons, and backgrounds.

### Material 3
Material 3 was chosen to maintain a modern and consistent visual design while taking advantage of well-structured components.

### Screen Navigation (Navigation)
The application uses Navigation Compose with centralized route definitions for parameterized destinations.

Navigation responsibilities are kept separate from screen specific logic, reducing coupling between screens, `ViewModels`, and the `NavigationGraph`.

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
