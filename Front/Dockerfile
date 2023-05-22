# Start with a base image containing Node.js runtime
FROM node:alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY package*.json ./

# Install the dependencies
RUN npm install

# Copy the entire Angular project to the container 
COPY . .

# Build the Angular app for production
RUN npm run build --prod

# Now, create another lightweight Nginx image to serve the angular build artifact
FROM nginx:alpine

# Remove the default Nginx website
RUN rm -rf /usr/share/nginx/html/*

# Copy the build output from the previous stage to the Nginx web root directory
COPY --from=build /app/dist/front /usr/share/nginx/html

# Expose port 80 of the container to the host machine
EXPOSE 80

# Start Nginx in foreground mode when the container starts
CMD ["nginx", "-g", "daemon off;"]
