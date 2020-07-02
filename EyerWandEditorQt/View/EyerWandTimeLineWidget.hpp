#ifndef EYER_WAND_TIMELINE_H
#define EYER_WAND_TIMELINE_H

#include <QWidget>
#include "EyerWand/EyerWand.hpp"

namespace Eyer {
    class EyerWandTimeLineWidget : public QWidget
    {
        Q_OBJECT
    public:
        explicit EyerWandTimeLineWidget(QWidget * parent = 0);
        ~EyerWandTimeLineWidget();

    protected:
        void paintEvent(QPaintEvent *);

        void mousePressEvent(QMouseEvent * event);
        void mouseReleaseEvent(QMouseEvent * event);
        void mouseDoubleClickEvent(QMouseEvent * event);
        void mouseMoveEvent(QMouseEvent * event);

    private:
        Eyer::WandTimeLine timeLine;
        Eyer::EyerWandContext wandContext;
    };
}

#endif // EYER_WAND_TIMELINE_H