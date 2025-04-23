# YouTube Manager - Project Guidelines

## Project Overview

YouTube Manager is a Spring Boot application that provides a user interface for interacting with YouTube data through the YouTube API. The application allows users to search for and view information about YouTube channels, with potential for expansion to other YouTube entities like videos and playlists.

## Technology Stack

- **Java 21**: The application is built using Java 21.
- **Spring Boot 3.4.4**: Provides the foundation for the application, including dependency injection, web server, and more.
- **Vaadin 24.7.0**: A web framework for building modern web applications in Java without having to write HTML, CSS, or JavaScript.
- **YouTube Data API v3**: Used to fetch data from YouTube.
- **Maven**: Used for dependency management and build automation.
- **Lombok**: Used to reduce boilerplate code.

## Architecture

The application follows a layered architecture with clear separation of concerns:

### Core Layer

Located in the `br.com.youtubemanager.core` package, this layer contains:

- **Utility Classes**: `DateUtils` and `NumberUtils` for common operations.
- **Configuration**: `YouTubeConfiguration` for setting up the YouTube API client.
- **Reusable UI Components**: In the `vaadin.component` subpackage, including:
  - `SearchButton`: A customized button for search operations.
  - `TextOutputWithIcon`: A component for displaying text with an associated icon.
  - `YouTubeNotification`: A notification component for YouTube-related operations.

### Feature Layers

Currently, the application has one main feature layer for channel operations:

#### Channel Feature

Located in the `br.com.youtubemanager.channel` package, this feature includes:

- **Data Transfer Objects**: `ChannelDTO` for transferring channel data between layers.
- **Exceptions**: `ChannelNotFoundException` for handling channel lookup failures.
- **Web Components**: In the `web` subpackage:
  - `ChannelService`: Service for channel operations, including fetching channel data from YouTube.
  - `ChannelView`: Vaadin view for displaying and interacting with channel data.
  - `ChannelCard`: UI component for displaying channel information in a card format.

## Application Flow

1. The application starts with the `YoutubeManagerApplication` class, which is a standard Spring Boot application with Vaadin integration.
2. Users interact with the `ChannelView`, which provides a search interface for YouTube channels.
3. When a user searches for a channel, the `ChannelService` uses the YouTube API to fetch the channel data.
4. The channel data is displayed using the `ChannelCard` component.
5. Notifications are shown using the `YouTubeNotification` component for success, errors, or other messages.

## Configuration

The application is configured using the `application.yaml` file, which includes:

- **Spring Application Name**: Set to "youtube-manager".
- **Vaadin Configuration**: Set to automatically launch the browser.
- **YouTube API Key**: Required for accessing the YouTube Data API.

## Development Guidelines

### Code Style

- The project uses the Spring Java Format plugin to enforce consistent code style.
- Lombok is used to reduce boilerplate code, particularly for data classes.

### Package Structure

- Packages are organized by feature, with common utilities and components in the `core` package.
- Each feature package should contain its own DTOs, services, and web components.

### UI Components

- Vaadin is used for building the UI.
- Custom components should be placed in the appropriate `component` subpackage.
- Reusable components should be placed in the `core.vaadin.component` package.

### Error Handling

- Custom exceptions should be created for specific error scenarios.
- The `YouTubeNotification` component should be used for displaying error messages to users.

## Future Enhancements

Potential areas for expansion include:

- Adding support for videos and playlists.
- Implementing user authentication and personalized content.
- Adding analytics and reporting features.
- Enhancing the UI with more interactive elements and visualizations.