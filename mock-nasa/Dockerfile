FROM python:alpine3.17
WORKDIR /app
COPY ./requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
EXPOSE 9050
CMD ["python3", "server.py"]
