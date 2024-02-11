# Monde de Dév MDD Client
MDD Client is the front-end of the MDD application, a social network for developer, powered by Angular framework.

## Prerequisites
- Angular 17.1.1
- Node 20.10.0
- Npm 10.4.0

## Installation

First install a node version manager like [nvm](https://github.com/nvm-sh/nvm#installing-and-updating).

Execute `nvm install 20.10.0` to install Node and npm.

Execute `npm install -g @angular/17.1.1` to install Angular.

Run `npm install` to install all the dependencies.

## Run

To run the app in dev mode, execute `npm run start`.

In a browser go to `http://localhost:4200` to use the app (IMPORTANT: start the back-end before !).

## Use

Begin by register yourself on the app and log in.

Some topics are already available for subscribing in at `http://localhost:4200/topics`.

Go to `http://localhost:4200/posts`, you can now publish an article selecting a topic.

Enjoy !!!

## Folder structure
```bash
└── src
    ├── app
    │   ├── components
    │   │   ├── home
    │   │   ├── me
    │   │   └── not-found
    │   ├── core
    │   │   └── components
    │   │       └── header
    │   ├── features
    │   │   ├── auth
    │   │   │   ├── components
    │   │   │   │   ├── login
    │   │   │   │   └── register
    │   │   │   ├── models
    │   │   │   └── services
    │   │   ├── comments
    │   │   │   ├── components
    │   │   │   │   ├── form
    │   │   │   │   └── list
    │   │   │   ├── models
    │   │   │   └── services
    │   │   ├── posts
    │   │   │   ├── components
    │   │   │   │   ├── detail
    │   │   │   │   ├── form
    │   │   │   │   └── list
    │   │   │   ├── models
    │   │   │   └── services
    │   │   └── topics
    │   │       ├── components
    │   │       │   ├── list
    │   │       │   └── my-topics
    │   │       ├── models
    │   │       └── services
    │   ├── guards
    │   ├── interceptors
    │   ├── models
    │   ├── services
    │   └── shared
    ├── assets
    │   ├── favicon
    │   └── img
    └── environments
```

## Author

Gilles BERNARD (@tipikae)
