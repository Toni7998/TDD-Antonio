import java.time.LocalDate;

public class CompteCorrent {

    // Propiets
    private double saldo;
    private double transferenciaDiaria;
    private LocalDate ultimaDataTransferencia;
    private static final double MAXIM = 6000.00;

    /**
     * Constructor del Compte Corent
     */
    public CompteCorrent() {
        this.saldo = 0.00;
    }

    /**
     * 
     * @return
     */
    public double getSaldo() {
        return this.saldo;
    }

    /**
     * Funció per ingressar la quantitat que passem per paràmetre
     * 
     * @param quantitat
     */
    public void ingressar(double quantitat) {

        if (quantitat >= 0 && quantitat <= MAXIM && Math.floor(quantitat * 100) == quantitat * 100) {
            this.saldo += quantitat;
        }
        
    }

    /**
     * Funció per retirar la quantitat que passem per paràmetre
     * 
     * @param quantitat
     */
    public void retirar(double quantitat) {

        if (quantitat >= 0 && quantitat <= this.saldo && Math.floor(quantitat * 100) == quantitat * 100) {
            this.saldo -= quantitat;
        }

    }

    /**
     * Funció per transferir a la compte destí que passem com a paràmetre
     * 
     * @param compteDesti
     * @param quantitat
     */
    public void transferir(CompteCorrent compteDesti, double quantitat) {

        if (quantitat >= 0 && quantitat <= this.saldo && Math.floor(quantitat * 100) == quantitat * 100) {

            if (LocalDate.now().equals(this.ultimaDataTransferencia)) {
            
                if (this.transferenciaDiaria + quantitat <= MAXIM) {
                    this.saldo -= quantitat;
                    compteDesti.ingressar(quantitat);
                    this.transferenciaDiaria += quantitat;
                }

            } else {
                this.saldo -= quantitat;
                compteDesti.ingressar(quantitat);
                this.transferenciaDiaria = quantitat;
                this.ultimaDataTransferencia = LocalDate.now();
            }
        }
    }
}
