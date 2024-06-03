package com.example.navalbattlefinal.model;

public class NavalBattle {
    public enum EstadoCasilla {
        VACIA('.'),
        OCUPADA('X'),
        ATACADA('-');

        private final char simbolo;

        EstadoCasilla(char simbolo) {
            this.simbolo = simbolo;
        }

        public char getSimbolo() {
            return this.simbolo;
        }
    }

    public enum EstadoBarco {
        POSICIONADO, HUNDIDO, NO_POSICIONADO
    }
}
