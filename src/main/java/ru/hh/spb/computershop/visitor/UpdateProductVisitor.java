package ru.hh.spb.computershop.visitor;

import ru.hh.spb.computershop.data.ComputerType;
import ru.hh.spb.computershop.data.NotebookSize;
import ru.hh.spb.computershop.entities.Computer;
import ru.hh.spb.computershop.entities.HDD;
import ru.hh.spb.computershop.entities.Monitor;
import ru.hh.spb.computershop.entities.Notebook;

public class UpdateProductVisitor implements ProductVisitor {
    @Override
    public void visitComputer(Computer computer, String computerType) {
        ComputerType type = ComputerType.fromValue(computerType);
        computer.setComputerType(type);
    }

    @Override
    public void visitNotebook(Notebook notebook, String notebookSize) {
        NotebookSize size = NotebookSize.fromValue(Integer.parseInt(notebookSize));
        notebook.setNotebookSize(size);
    }

    @Override
    public void visitMonitor(Monitor monitor, String diagonal) {
        Byte size = Byte.parseByte(diagonal);
        monitor.setDiagonal(size);
    }

    @Override
    public void visitHDD(HDD hdd, String volume) {
        Long size = Long.parseLong(volume);
        hdd.setVolume(size);
    }
}
