# Vue.js Project Setup Documentation

## Overview

This document provides step-by-step instructions on how to set up a Vue.js project using Vue CLI with Vuetify integration. It also covers the installation of essential libraries for the project's operation and development environment configuration.

## Prerequisites

Ensure Node.js version 21.7.1 is installed, which includes npm version 10.5. This can be verified by running `node -v` and `npm -v` in the terminal.

## Vue Project Initialization

1. **Install Vue CLI:**
   - Install Vue CLI globally using npm:
     ```
     npm install -g @vue/cli@3.4.21
     ```
   - Create a new Vue.js project:
     ```
     npm create vue@3.4.21
     ```
   - Answer the prompts as follows:
     - Vue router: Yes (to manage routing)
     - Vitest: Yes (for testing)
     - ESLint and Prettier: Yes (for code formatting)
     - All other options: No

2. **Project Setup:**
   - Navigate into your new project directory:
     ```
     cd <project-name>
     ```
   - Install project dependencies:
     ```
     npm install
     ```
   - Start the development server:
     ```
     npm run dev
     ```

## Vuetify Installation

- Install Vuetify within your project:
  ```
  npm install vuetify
  ```

- Update your `main.js` file to include Vuetify:

  ```javascript
  // Vuetify
  import 'vuetify/styles'
  import { createVuetify } from 'vuetify'
  import { aliases, mdi } from 'vuetify/iconsets/mdi'
  import * as components from 'vuetify/components'
  import * as directives from 'vuetify/directives'

  const vuetify = createVuetify({
      components,
      directives,
      icons: {
        defaultSet: 'mdi',
        aliases,
        sets: {
          mdi,
        },
      },
    })

  // Create the Vue instance and integrate Vuetify
  const app = createApp(App)
  app.use(vuetify)
  ```

## Including Material Design Icons

For Vuetify to display icons correctly, include the following line in the `<head>` section of your `index.html` file:

```html
<link href="https://cdn.jsdelivr.net/npm/@mdi/font@7.x/css/materialdesignicons.min.css" rel="stylesheet">
```

This CDN link imports the Material Design Icons stylesheet required by Vuetify components.

With this line added to your `index.html`, Vuetify will have access to the complete range of icons provided by the Material Design Icons library, ensuring that any icon you use in your Vue components will render as expected.

## Library Installation

Install additional libraries needed for various functionalities:

- **axios**: For making API requests.
- **animate.css**: For adding CSS animations.
- **crypto-js**: For cryptographic operations.
- **vuelidate**: For form validation.

```bash
npm install axios animate.css crypto-js vuelidate
```

## Automatic Documentation Setup

Install JSDoc libraries to generate documentation:

```bash
npm install jsdoc jsdoc-vuejs better-docs
```

Create a `jsdoc.json` configuration file:

```json
{
    "tags": {
        "allowUnknownTags": true
    },
    "source": {
        "include": ["./src"],
        "includePattern": "\\.(vue|js)$"
    },
    "plugins": [
        "node_modules/jsdoc-vuejs",
        "better-docs/category"
    ],
    "opts": {
        "encoding": "utf8",
        "destination": "docs/",
        "recurse": true,
        "verbose": true,
        "readme": "./README.md",
        "template": "node_modules/better-docs"
    }
}
```

## Vite Configuration

Specify the desired port in `vite.config.js`:

```javascript
import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [
    vue(),
  ],
  server: {
    host: true,
    port: 8000, // Port to be used in Docker
    watch: {
      usePolling: true
    }
  },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
```

## Post-Clone/Pull Instructions

After cloning the project or pulling new changes, if new packages are installed, run:

```bash
npm install
```

This ensures all dependencies are correctly installed.

## Code Structure

The project is organized as follows:

- **router**: Contains the routing configuration file.
- **views**: Holds the application's pages.
- **components**: Includes subdirectories for Vue components and tests.
- **assets**: Stores styles and other static assets.
