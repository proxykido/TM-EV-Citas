import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        int opcion = 0;
        ArrayList<Doctor> doctores = new ArrayList<Doctor>();
        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
        ArrayList<Cita> citas = new ArrayList<Cita>();
        String usuario = "";
        String password = "";
        String nombre = "";
        String valorExtra = "";
        Scanner in = new Scanner(System.in);
        // Leer usuario
        System.out.println("Ingresa usuario: ");
        usuario = System.console().readLine();
        // Leer contraseña
        System.out.println("Ingresa contraseña: ");
        password = System.console().readLine();

        if(!Objects.equals(usuario, "admin") || !Objects.equals(password, "admin")) {
            throw new Exception("Credenciales invalidas");
        }
        do {
            System.out.println("Selecciona la opción deseada:");
            System.out.println("1. Alta Doctores");
            System.out.println("2. Alta Pacientes");
            System.out.println("3. Agendar Cita");
            System.out.println("4. Mostrar Citas");
            System.out.println("5. Relacionar Cita");
            System.out.println("0. Salir");
            opcion = in.nextInt();
            // Leer opcion
            if(opcion == 1) {
                System.out.println("Nombre del doctor:");
                nombre = System.console().readLine();
                System.out.println("Especialidad: ");
                valorExtra = System.console().readLine();
                doctores.add(new Doctor(doctores.size()+1, nombre,valorExtra));
            }
            if(opcion == 2) {
                System.out.println("Nombre del paciente:");
                nombre = System.console().readLine();
                pacientes.add(new Paciente(pacientes.size()+1,nombre));
            }
            if(opcion == 3) {
                System.out.println("Fecha/hora de la cita: (YYYY-MM-DD HH:MM)");
                LocalDateTime fh = LocalDateTime.now();
                String fhtxt = System.console().readLine();
                fhtxt = fhtxt.replace(" ","T");
                fhtxt = fhtxt+":00";
                fh = LocalDateTime.parse(fhtxt);
                System.out.println("Motivo de la cita:");
                valorExtra = System.console().readLine();
                citas.add(new Cita(citas.size()+1, fh, valorExtra) );
            }
            if(opcion == 4) {
                System.out.println("Citas:");
                for (int i = 0; i < citas.size(); i++) {
                    Cita x = citas.get(i);
                    System.out.println("Cita # " + x.id_cita);
                    System.out.println("Motivo: " + x.motivo);
                    System.out.print("Doctor: ");
                    if (x.id_doctor < 1) {
                        System.out.print("N/A");
                    } else {
                        Doctor d = doctores.get(x.id_doctor - 1);
                        System.out.print(d.nombre);
                    }
                    System.out.print(" Paciente: ");
                    if (x.id_paciente < 1) {
                        System.out.println("N/A");
                    } else {
                        Paciente p = pacientes.get(x.id_paciente - 1);
                        System.out.println(p.nombre);
                    }
                }
            }
            if(opcion == 5) {
                System.out.println("Selecciona la citas a modificar:");
                for(int i=0; i<citas.size(); i++) {
                    Cita x = citas.get(i);
                    System.out.println("Cita # "+x.id_cita);
                    System.out.println("Motivo: "+x.motivo);
                    System.out.print("Doctor: ");
                    if(x.id_doctor <1) {
                        System.out.print("N/A");
                    } else {
                        Doctor d = doctores.get(x.id_doctor-1);
                        System.out.print(d.nombre);
                    }
                    System.out.print(" Paciente: ");
                    if(x.id_paciente < 1) {
                        System.out.println("N/A");
                    }
                    else {
                        Paciente p = pacientes.get(x.id_paciente-1);
                        System.out.println(p.nombre);
                    }
                }
                int opcioncita = in.nextInt();
                System.out.println("Escoge al doctor:");
                for(int i=0; i<doctores.size(); i++) {
                    Doctor x = doctores.get(i);
                    System.out.println(x.id_doctor+" : "+x.nombre+" ("+x.especialidad+")");
                }
                int opciondoctor = in.nextInt();
                System.out.println("Escoge al paciente:");
                for(int i=0; i<pacientes.size(); i++) {
                    Paciente x = pacientes.get(i);
                    System.out.println(x.id_paciente+" : "+x.nombre);
                }
                int opcionpaciente = in.nextInt();
                Cita x = citas.get(opcioncita-1);
                x.SetDoctor(opciondoctor);
                x.SetPaciente(opcionpaciente);
                citas.set((opcioncita-1),x);
            }

        } while(opcion > 0);
    }
}