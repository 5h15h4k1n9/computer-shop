package ru.hh.spb.computershop.entities;

import jakarta.persistence.Entity;

import ru.hh.spb.computershop.data.NotebookSize;
import ru.hh.spb.computershop.data.ProductType;

@Entity

public class Notebook extends Product {

    NotebookSize notebookSize;

    public Notebook() {
        super();

        this.type = ProductType.NOTEBOOK;
        this.notebookSize = NotebookSize.UNKNOWN;
    }

    public Notebook(
            String serialNumber,
            Manufacturer manufacturer,
            Long cost,
            Long count,
            NotebookSize notebookSize
    ) {
        super(serialNumber, manufacturer, cost, count);

        this.type = ProductType.NOTEBOOK;
        this.notebookSize = notebookSize;
    }

    public NotebookSize getNotebookSize() {
        return notebookSize;
    }

    public String toString() {
        String superString = super.toString();
        return "Notebook{" +
                superString +
                ", notebookSize=" + notebookSize.getValue() +
                '}';
    }
}
