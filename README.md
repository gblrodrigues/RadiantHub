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
* Immersive fullscreen agent presentation:
  * Blurred cinematic background  
  * Centered agent artwork  
* Agent details:
  * Name  
  * Role  
  * Description  

### Agent Dossier
* BottomSheet with detailed agent abilities
* Ability list including:
  * Icon  
  * Ability name  
  * Description

### Maps
* Browse all Valorant maps
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
* Organized by tier divisions
* Tier icons and progression visualization
* Search integration with automatic navigation

### Favorites
* Filtering by All, Agents and Cards categories
* Persistent local favorites system for agents and player cards
* Undo support when removing favorites
* Favorite restoration to original position
* Dynamic favorite toggling directly from agent pages

### Search
* Global search across agents, maps and player cards and competitive tiers
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
* Public API integration with Valorant API
* Practicing application localization and internationalization (i18n)
* Dynamic UI rendering using Compose State
* Runtime language switching
* Persistent language preferences
* API content localization

## Preview
### Home Screen
<img src="https://github.com/user-attachments/assets/ac2d8c5b-8d04-4122-8962-d95b6c0d5d52" width="250"/>

### Agents Screen and Agent Abilities
<img src="https://github.com/user-attachments/assets/566ddade-80b6-4977-aa6d-4a16aacf48c8" width="250"/>
<img src="https://github.com/user-attachments/assets/d841cfac-046f-4fc8-a82f-d0f03451bc39" width="250"/>

### Maps Screen
<img src="https://github.com/user-attachments/assets/b82f84c0-95e1-40f9-9524-e8610577eb74" width="250"/>
<img src="https://github.com/user-attachments/assets/33b7fa4d-d6f7-418e-a54e-0980621975bf" width="250"/>

### Cards Screen
<img src="https://github.com/user-attachments/assets/824b38e5-80d4-4488-9450-f518978075fb" width="250"/>

### Tiers Screen
<img src="https://github.com/user-attachments/assets/3ccef472-e6b7-46a6-b206-6434bb2f06ae" width="250"/>

### Favorites Screen
<img src="https://github.com/user-attachments/assets/e5d3a9a6-3c82-406e-837c-f75ac3255356" width="250"/>
<img src="https://github.com/user-attachments/assets/2840d371-92bc-4bf9-9b12-a7be0f579c90" width="250"/>
<img src="https://github.com/user-attachments/assets/399726f9-550e-4e4c-8fc4-7cc452896ac6" width="250"/>

### Search 
<img src="https://github.com/user-attachments/assets/c0af0f39-fce6-42b3-9b0a-15a4ac65af03" width="250"/>
<img src="https://github.com/user-attachments/assets/0d43acfe-c500-4f09-9d76-94cf887c21ad" width="250"/>

Below I will provide a video showing how the application looks:  
> https://github.com/user-attachments/assets/56936187-aea7-4f68-b48c-dde0c62b02c9

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
