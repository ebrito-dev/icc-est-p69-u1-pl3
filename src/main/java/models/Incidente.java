package models;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Incidente implements Comparable<Incidente> {

    private final String codigo;
    private final int severidad;
    private final int minutosRestantes;
    private final List<ServicioAfectado> servicios;
    private final LocalDateTime registradoEn;

    public Incidente(String codigo, int severidad, int minutosRestantes,
            List<ServicioAfectado> servicios, LocalDateTime registradoEn) {
        // Objects.requireNonNull(codigo, "codigo");
        // if (!codigo.matches("[A-Za-z0-9-]+") || severidad < 1 || severidad > 4) {
        // throw new IllegalArgumentException("Código o severidad inválidos");
        // }
        this.codigo = codigo;
        this.severidad = severidad;
        this.minutosRestantes = minutosRestantes;
        this.servicios = List.copyOf(servicios);
        // this.registradoEn = Objects.requireNonNull(registradoEn, "registradoEn");
        this.registradoEn = registradoEn;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getSeveridad() {
        return severidad;
    }

    public int getMinutosRestantes() {
        return minutosRestantes;
    }

    public List<ServicioAfectado> getServicios() {
        return servicios;
    }

    public LocalDateTime getRegistradoEn() {
        return registradoEn;
    }

@Override
    public int compareTo(Incidente otro) {
        Objects.requireNonNull(otro, "No se puede comparar con null");
        Comparator<Incidente> comparador = Comparator
                .comparingInt(Incidente::getSeveridad)
                .thenComparingInt(Incidente::getMinutosRestantes)
                .thenComparing(
                        (Incidente i) -> i.getServicios() == null ? 0 : i.getServicios().size(),
                        Comparator.reverseOrder()
                )
                .thenComparing(
                        (Incidente i) -> {
                            if (i.getServicios() == null) return 0L;
                            long sumatoriaUserA = 0L;
                            for (ServicioAfectado servicio : i.getServicios()) {
                                if (servicio != null) {
                                    sumatoriaUserA += servicio.usuariosAfectados();
                                }
                            }
                            return sumatoriaUserA;
                        },
                        Comparator.reverseOrder()
                )
                .thenComparing(Incidente::getRegistradoEn)
                .thenComparing(Incidente::getCodigo, String.CASE_INSENSITIVE_ORDER);

        return comparador.compare(this, otro);
    }
    @Override
    public String toString() {
        return "%s | severidad=%d | minutos=%d | servicios=%d | fecha=%s"
                .formatted(codigo, severidad, minutosRestantes, servicios.size(), registradoEn);
    }
}
