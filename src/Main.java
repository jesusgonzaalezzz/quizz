import java.util.*;

public class Main {
    private static final String QUIZ_NAME = "Quiz de Cultura General"; // Nombre del quiz
    private static final String[][] PREGUNTAS_RESPUESTAS = {
            {"¿La Gran Muralla China es visible desde el espacio?", "si"},
            {"¿El helio es un gas noble?", "si"},
            {"¿La Estatua de la Libertad fue un regalo de Francia a Estados Unidos?", "si"},
            {"¿El idioma oficial de Brasil es el español?", "no"},
            {"¿La Gran Barrera de Coral está ubicada en el océano Atlántico?", "no"},
            {"¿La pizza se originó en Italia?", "si"},
            {"¿La Mona Lisa fue pintada por Vincent van Gogh?", "no"},
            {"¿La Capilla Sixtina se encuentra en el Vaticano?", "si"},
            {"¿La Gran Pirámide de Giza es la única de las siete maravillas del mundo antiguo que aún existe?", "si"},
            {"¿La Estrella de la Muerte es una nave espacial real?", "no"},
            {"¿El pingüino es un mamífero?", "no"},
            {"¿El Everest es la montaña más alta de la Tierra?", "si"},
            {"¿La Fórmula 1 es una competición de carreras de caballos?", "no"},
            {"¿La Mona Lisa está expuesta en el Louvre?", "si"},
            {"¿La Tierra es plana?", "no"},
            {"¿La Mona Lisa es una pintura al óleo?", "si"},
            {"¿El Sahara es el desierto más grande del mundo?", "si"},
            {"¿La guitarra eléctrica fue inventada en el siglo XIX?", "no"},
            {"¿El Sol es una estrella?", "si"},
            {"¿Los delfines son mamíferos marinos?", "si"}
    };

    private static String[] seleccionarPreguntas(int numPreguntas) {
        int totalPreguntas = PREGUNTAS_RESPUESTAS.length;
        String[] seleccionadas = new String[numPreguntas];
        boolean[] usadas = new boolean[totalPreguntas];
        for (int i = 0; i < numPreguntas; i++) {
            int index;
            do {
                index = (int) (Math.random() * totalPreguntas);
            } while (usadas[index]);
            seleccionadas[i] = PREGUNTAS_RESPUESTAS[index][0];
            usadas[index] = true;
        }
        return seleccionadas;
    }

    private static String[] realizarPreguntas(String[] preguntas) {
        Scanner scanner = new Scanner(System.in);
        String[] respuestas = new String[preguntas.length];
        for (int i = 0; i < preguntas.length; i++) {
            boolean respuestaValida = false;
            while (!respuestaValida) {
                System.out.println(preguntas[i]);
                String respuesta = scanner.nextLine().toLowerCase();

                if (respuesta.equals("si") || respuesta.equals("no")) {
                    respuestas[i] = respuesta;
                    respuestaValida = true;
                } else {
                    System.out.println("Respuesta inválida. Por favor, ingresa 'si' o 'no'.");
                }
            }
        }
        return respuestas;
    }

    private static int comprobarRespuestas(String[] respuestasUsuario, String[] respuestasCorrectas) {
        int aciertos = 0;
        for (int i = 0; i < respuestasUsuario.length; i++) {
            if (respuestasUsuario[i].equals(respuestasCorrectas[i])) {
                aciertos++;
            }
        }
        return aciertos;
    }

    private static void mostrarResultados(int respuestasCorrectas, int numPreguntas) {
        double porcentajeAciertos = (double) respuestasCorrectas / numPreguntas * 100;
        System.out.printf("Has acertado %d de %d preguntas (%.2f%%)\n", respuestasCorrectas, numPreguntas, porcentajeAciertos);
        if (porcentajeAciertos >= 0 && porcentajeAciertos < 34) {
            System.out.println("Todavía tienes que aprender un poco más. ¡No te rindas!");
        } else if (porcentajeAciertos >= 34 && porcentajeAciertos < 67) {
            System.out.println("¡Buen trabajo! Estás en el camino correcto, pero aún puedes mejorar.");
        } else if (porcentajeAciertos >= 67 && porcentajeAciertos < 100) {
            System.out.println("¡Excelente! Tienes un excelente nivel de conocimientos.");
        } else {
            System.out.println("¡Increíble! ¡Has acertado TODAS las preguntas! Eres un verdadero genio.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al " + QUIZ_NAME + "!");

        boolean jugarDeNuevo = true;
        while (jugarDeNuevo) {
            int numPreguntas;
            do {
                System.out.println("Selecciona cuántas preguntas quieres responder (mínimo 5, máximo 20):");
                numPreguntas = scanner.nextInt();
                scanner.nextLine();
            } while (numPreguntas < 5 || numPreguntas > 20);

            String[] preguntas = seleccionarPreguntas(numPreguntas);
            String[] respuestasCorrectas = new String[numPreguntas];
            for (int i = 0; i < numPreguntas; i++) {
                for (String[] preguntaRespuesta : PREGUNTAS_RESPUESTAS) {
                    if (preguntaRespuesta[0].equals(preguntas[i])) {
                        respuestasCorrectas[i] = preguntaRespuesta[1];
                        break;
                    }
                }
            }

            String[] respuestasUsuario = realizarPreguntas(preguntas);
            int respuestasCorrectasCount = comprobarRespuestas(respuestasUsuario, respuestasCorrectas);
            mostrarResultados(respuestasCorrectasCount, numPreguntas);

            System.out.println("¿Quieres jugar de nuevo? (si/no)");
            String respuesta = scanner.nextLine().toLowerCase();
            jugarDeNuevo = respuesta.equals("si");
        }
        System.out.println("¡Gracias por jugar el " + QUIZ_NAME + "!");
        scanner.close();
    }
}