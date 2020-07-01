#include "MainWindow.h"
#include "ui_MainWindow.h"
#include "View/EyerWandTimeLineWidget.hpp"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    Eyer::EyerWandTimeLineWidget * timeLine = new Eyer::EyerWandTimeLineWidget(this);

    ui->time_line_layout->addWidget(timeLine);
}

MainWindow::~MainWindow()
{
    delete ui;
}
