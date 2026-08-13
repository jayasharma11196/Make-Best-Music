# 🎵 Make Best Music

**Make Best Music** is a modern, premium Android application built with Kotlin and Jetpack Compose. It serves as an AI-powered music creation hub, allowing users to generate original tracks, create AI covers with custom voices, and share their creations within a vibrant community.

## 📱 Features & Core Screens

### 1. ✨ Splash & Onboarding
- **Visual Splash Screen:** Engaging entry point with brand identity.
- **Modern UI:** Clean, dark-themed aesthetic designed for a premium music experience.

### 2. 🔐 Authentication & Security
- **Credentials Flow:** Seamless Login and Sign Up pages.
- **Validation:** Live error validation for user inputs (Email, Password) to ensure data integrity.

### 3. 🏠 Smart Home & Community
- **Trending Music:** Discovery feed with "Trending Now", "Recommended", and "New Releases".
- **Category Explorer:** Browse music by genres like Pop, Rock, Electronic, and more with custom visual cards.
- **Community Interaction:** View posts, likes, comments, and play counts.
- **Post & Earn:** Users can post their creations to the community and earn rewards.

### 4. 🎼 AI Music Creation
- **Creation Modes:** Choose between **Simple** and **Custom** modes for music generation.
- **Instrumental Toggle:** Option to generate purely instrumental tracks.
- **Lyric Integration:** Input custom lyrics for AI-generated vocals.

### 5. 🎤 AI Covers
- **Voice Selection:** Switch between "My Voice" and "Public Voice" for covers.
- **Advanced Control:** Fine-tune timbre and other vocal parameters for the perfect AI cover.

### 6. 📚 Library & Personal Workspace
- **My Creations:** A dedicated space to manage all generated music and covers.
- **Search:** Quickly find tracks or community posts using the integrated search feature.

### 7. 💰 Pricing & Credits
- **Credit System:** Transparent credit tracking for AI generations.
- **Subscription Plans:** Pricing screens detailing different tiers for music creators.

### 8. 👤 User Profile & Settings
- **Customization:** Manage profile details, notifications, and app preferences.
- **Support:** Integrated FAQ and feedback system.

---

## 🛠️ Tech Stack

The application leverages modern Android development best practices:

| Component | Library / Framework | Description |
|-----------|---------------------|-------------|
| **UI Framework** | [Jetpack Compose](https://developer.android.com/jetpack/compose) | Declarative UI for a fluid and responsive user experience. |
| **Language** | [Kotlin](https://kotlinlang.org/) | 100% Kotlin-based codebase. |
| **Navigation** | [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) | Standard Android navigation component for Compose. |
| **Image Loading** | [Coil](https://coil-kt.github.io/coil/) | Fast and lightweight image loading for album art and avatars. |
| **Architecture** | MVVM | Clean separation of concerns using ViewModels and StateFlow. |
| **Responsiveness** | Custom `sdp` / `ssp` | Scalable DP and SP units to ensure UI consistency across different screen sizes. |

---

## 🚀 How to Run the Application

### Prerequisites
- Android Studio Ladybug or newer.
- JDK 17+.
- Android Device or Emulator (API 24+).

### Steps to Run
1. Clone the repository.
2. Open the project in **Android Studio**.
3. Let Gradle synchronize all dependencies.
4. Select the `app` configuration.
5. Click **Run** (or press `Shift + F10`).

---

## 📂 Project Structure

```text
.
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/make_best_music/
│           │   ├── ui/
│           │   │   ├── home/    # Main feature screens (Community, Create, AI Cover, etc.)
│           │   │   ├── login/   # Auth flow (Splash, Login)
│           │   │   ├── theme/   # Design system (Color, Type, Theme)
│           │   │   └── utils/   # Scalable UI utilities (sdp, ssp)
│           │   └── data/        # (Optional) Models and Repository logic
│           └── res/             # Android Resources (Drawables, Layouts, Mipmaps)
├── build.gradle.kts             # Project-level build configuration
└── settings.gradle.kts           # Module definitions
```

---

## 🔒 Form Validation Logic
All authentication and feedback forms implement strict validations:
- **Email:** Standard regex matching for valid email formats.
- **Required Fields:** Visual feedback and error messages if fields are left blank.
- **Interactive Feedback:** Real-time UI updates based on input validity.
