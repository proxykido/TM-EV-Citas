import java.time.LocalDateTime;

public class Cita {
    int id_cita;
    int id_doctor;
    int id_paciente;
    String motivo;
    LocalDateTime FechaHora = LocalDateTime.now();
    Cita(int id, LocalDateTime fh, String motivo) {
        this.id_cita = id;
        this.FechaHora = fh;
        this.motivo = motivo;
    }
    public void SetPaciente(int paciente) {
        this.id_paciente = paciente;
    }
    public void SetDoctor(int doctor) {
        this.id_doctor = doctor;
    }

}
