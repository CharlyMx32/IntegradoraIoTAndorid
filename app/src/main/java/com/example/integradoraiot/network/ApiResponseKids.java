package com.example.integradoraiot.network;

public class ApiResponseKids {

        private String status;
        private String message;

        public ApiResponseKids(String status, String message) {
            this.status = status;
            this.message = message;
        }

        // Getters y setters

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
}
