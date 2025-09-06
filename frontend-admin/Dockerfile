# Usa uma imagem Node.js para o build do React
FROM node:18-alpine AS build

# Define o diretorio de trabalho
WORKDIR /app

# Copia os arquivos de configuracao e dependencias e instala
COPY package*.json ./
RUN npm install

# Copia o restante do codigo e constroi a aplicacao
COPY . .
RUN npm run build

# Usa uma imagem leve do NGINX para servir os arquivos estaticos
FROM nginx:alpine

# Remove o arquivo de configuracao padrao do NGINX
RUN rm /etc/nginx/conf.d/default.conf

# Copia os arquivos de build do React para a pasta de servico do NGINX
COPY --from=build /app/build /usr/share/nginx/html

# Copia a configuracao personalizada do NGINX
COPY nginx.conf /etc/nginx/conf.d/

# Expoe a porta 80 do NGINX
EXPOSE 80

# Inicia o NGINX
CMD ["nginx", "-g", "daemon off;"]
