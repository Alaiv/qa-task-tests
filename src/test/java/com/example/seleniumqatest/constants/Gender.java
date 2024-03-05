package com.example.seleniumqatest.constants;

public enum Gender {
    Male{
        @Override
        public String toString() {
            return "Мужской";
        }
    },
    Female {
        @Override
        public String toString() {
            return "Женский";
        }
    }
}
