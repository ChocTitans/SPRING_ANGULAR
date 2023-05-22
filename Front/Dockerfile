# Base image
FROM node:alpine as builder

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install -g @angular/cli

RUN npm install

# Copy the project files
COPY . .

# Build the Angular app
RUN ng build --configuration=production

# Stage 2: Use a lighter base image
FROM nginx:alpine

# Copy the build artifacts from the previous stage
COPY --from=builder /app/dist/clinique-solis-web /usr/share/nginx/html

# Copy the nginx configuration file
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]