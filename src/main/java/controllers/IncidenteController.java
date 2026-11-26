package controllers;

import java.util.List;
import java.util.Objects;

import models.Incidente;

public class IncidenteController {
    public List<Incidente> sort(List<Incidente> incidentes) {
        incidentes.sort(null);
        return incidentes;
    }

    public int busquedaBinaria(List<Incidente> incidentes, Incidente objetivo) {
        Objects.requireNonNull(incidentes, "La lista no puede ser nula");
        Objects.requireNonNull(objetivo, "El objetivo no puede ser nulo");
        int low = 0;
        int high = incidentes.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = incidentes.get(mid).compareTo(objetivo);
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
