package ru.hh.spb.computershop.visitor;

import ru.hh.spb.computershop.entities.*;

public interface ProductVisitor {

    void visitComputer(Computer computer, String computerType);

    void visitNotebook(Notebook notebook, String notebookSize);

    void visitMonitor(Monitor monitor, String diagonal);

    void visitHDD(HDD hdd, String volume);
}
