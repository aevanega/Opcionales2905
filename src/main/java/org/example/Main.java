package org.example;

import org.example.dto.Personas;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Personas> personas = Arrays.asList(
                new Personas("Juan", 20 , "CDMX"),
                new Personas("Pedro", 30, "COL"),
                new Personas("Jose", 40,"USA"),
                new Personas("Maria", 50,"BRZ"),
                new Personas("Ana", 25, "COL"),
                new Personas("Luis", 28, "CDMX"),
                new Personas("Sofía", 35, "ITA")
        );

        //filtrar y mostrar
        personas.stream()
                .filter(e -> e.getEdad() > 25)
                .forEach(System.out::println);

        // Transformación y Agregación:
        List<String> upercaselist = personas.stream()
                .map(persona -> persona.getNombre().toUpperCase())
                .collect(Collectors.toList());

        System.out.println(upercaselist);

        // Ordenación y Depuración:
        personas.stream()
                .sorted((p1, p2) -> Integer.compare(p1.getEdad(), p2.getEdad()))
                .peek(persona -> System.out.println("Persona ordenada: " + persona.getNombre()))
                .forEach(persona -> System.out.println(persona.getNombre()));

        // Búsqueda Condicional:
        Optional<Personas> personaEncontrada = personas.stream()
                .filter(persona -> persona.getEdad() > 30 && persona.getCiudad().equals("CDMX"))
                .findAny();
        personaEncontrada.ifPresent(persona -> System.out.println("Persona encontrada: " + persona.getNombre()));

        // Agrupación y Partición:
        // Agrupar personas por ciudad
        Map<String, List<Personas>> personasPorCiudad = personas.stream()
                .collect(Collectors.groupingBy(Personas::getCiudad));

        System.out.println("Personas por ciudad: " + personasPorCiudad);

        // Particionar personas en menores de 30 y 30 o más
        Map<Boolean, List<Personas>> personasPorEdad = personas.stream()
                .collect(Collectors.partitioningBy(persona -> persona.getEdad() >= 30));

        System.out.println("Personas por edad (>=30): " + personasPorEdad.get(true));
        System.out.println("Personas por edad (<30): " + personasPorEdad.get(false));

        // Operaciones Combinadas:
        List<Personas> filtroOrden = personas.stream()
                .filter(persona -> persona.getEdad() > 25)
                .map(persona -> new Personas(persona.getNombre().toUpperCase(), persona.getEdad(), persona.getCiudad()))
                .sorted((p1, p2) -> Integer.compare(p1.getEdad(), p2.getEdad()))
                .collect(Collectors.toList());

        System.out.println(filtroOrden);

    }
}