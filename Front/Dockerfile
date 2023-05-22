# Base image
FROM node:alpine as builder

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the project files
COPY . .

# Build the Angular app
RUN npm run build --prod

# Stage 2: Use a lighter base image
FROM node:14-alpine

# Set the working directory
WORKDIR /app

# Copy the build artifacts from the previous stage
COPY --from=builder /app/dist /app/dist

# Expose port 4200
EXPOSE 4200

# Serve the Angular app using a simple Node.js HTTP server
CMD ["npx", "http-server", "-p", "4200", "/app/dist"]
