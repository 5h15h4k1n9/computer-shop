package ru.hh.spb.computershop.entities;

import jakarta.persistence.Entity;

import ru.hh.spb.computershop.data.NotebookSize;
import ru.hh.spb.computershop.data.ProductType;
import ru.hh.spb.computershop.visitor.ProductVisitor;

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
            Long price,
            Long count,
            NotebookSize notebookSize
    ) {
        super(serialNumber, manufacturer, price, count);

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

    public void setNotebookSize(NotebookSize size) {
        this.notebookSize = size;
    }

    public void accept(ProductVisitor visitor, String notebookSize) {
        visitor.visitNotebook(this, notebookSize);
    }
}
