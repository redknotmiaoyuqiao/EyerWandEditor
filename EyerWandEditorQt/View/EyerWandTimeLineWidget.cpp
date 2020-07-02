#include "EyerWandTimeLineWidget.hpp"
#include <QDebug>
#include <QPainter>
#include <QMouseEvent>

namespace Eyer {
    EyerWandTimeLineWidget::EyerWandTimeLineWidget(QWidget * parent) : QWidget(parent) , wandContext(1920, 1080, 30)
    {
        setMinimumHeight(300);
        setMaximumHeight(400);

        timeLine.SetWandCtx(&wandContext);
    }

    EyerWandTimeLineWidget::~EyerWandTimeLineWidget()
    {

    }

    void EyerWandTimeLineWidget::paintEvent(QPaintEvent *)
    {

        QPainter painter(this);
        painter.setRenderHints(QPainter::Antialiasing | QPainter::TextAntialiasing);

        // qDebug() << "w: " << this->rect().width() << " h:" << this->rect().height() << endl;
        timeLine.SetWH(this->rect().width(), this->rect().height());

        Eyer::WandTimeLineDrawEventList eventList;
        timeLine.Draw(eventList);

        int eventCount = eventList.GetEventCount();
        // qDebug() << "event count: " << eventCount << endl;
        for(int i=0;i<eventCount;i++){

            WandTimeLineDrawEvent * event = nullptr;
            eventList.GetEvent(event, i);
            if(event == nullptr){
                continue;
            }
            if(event->GetType() == Eyer::WandTimeLineDrawEventType::LINE){
                // qDebug() << "event line" << endl;
                Eyer::WandTimeLineDrawEvent_Line * lineEvent = (Eyer::WandTimeLineDrawEvent_Line *)event;
                Eyer::EyerVec2 p1;
                Eyer::EyerVec2 p2;
                lineEvent->GetLine(p1, p2);
                EyerVec4 color;
                lineEvent->GetColor(color);

                QColor c(color.x() * 255.0, color.y() * 255.0, color.z() * 255.0, color.w() * 255.0);
                painter.setPen(QPen(c, lineEvent->GetStrokeWidth()));

                painter.drawLine(p1.x(), p1.y(), p2.x(), p2.y());

            }

            if(event->GetType() == Eyer::WandTimeLineDrawEventType::RECT){
                // qDebug() << "event rect" << endl;
                Eyer::WandTimeLineDrawEvent_Rect * rectEvent = (Eyer::WandTimeLineDrawEvent_Rect *)event;
                Eyer::EyerVec2 start;
                Eyer::EyerVec2 end;
                rectEvent->GetRect(start, end);
                EyerVec4 color;
                rectEvent->GetColor(color);

                QColor c(color.x() * 255.0, color.y() * 255.0, color.z() * 255.0, color.w() * 255.0);
                painter.setPen(QPen(c, 2));
                painter.setBrush(c);
                painter.drawRect(start.x(), start.y(), end.x() - start.x(), end.y() - start.y());
            }

            if(event->GetType() == Eyer::WandTimeLineDrawEventType::TEXT){
                // qDebug() << "event text" << endl;
                Eyer::WandTimeLineDrawEvent_Text * textEvent = (Eyer::WandTimeLineDrawEvent_Text *)event;

                EyerVec4 color;
                textEvent->GetColor(color);

                EyerString text;
                float size;
                EyerVec2 start;
                EyerString fontStyle;
                textEvent->GetText(text, size, start, fontStyle);

                QColor c(color.x() * 255.0, color.y() * 255.0, color.z() * 255.0, color.w() * 255.0);
                painter.setPen(QPen(c, 2));
                painter.setBrush(c);

                QFont f("宋体", size, QFont::Thin, false);
                painter.setFont(f);

                painter.drawText(start.x(), start.y(), QString(text.str));
            }

            if(event->GetType() == Eyer::WandTimeLineDrawEventType::BITMAP_SNAPSHOT){
                // qDebug() << "event snapshot" << endl;
                Eyer::WandTimeLineDrawEvent_BitmapSnapshot * shapshotEvent = (Eyer::WandTimeLineDrawEvent_BitmapSnapshot *)event;

                Eyer::EyerVec4 src;
                shapshotEvent->GetSrc(src);

                Eyer::EyerVec4 dist;
                shapshotEvent->GetDist(dist);

                Eyer::EyerString path;
                path = shapshotEvent->GetPath();

                double time = shapshotEvent->GetTime();
            }
        }
    }

    void EyerWandTimeLineWidget::mousePressEvent(QMouseEvent * event)
    {
        float x = event->x() * 1.0f;
        float y = event->y() * 1.0f;
        timeLine.OnTouchDown(x, y);
        update();
    }

    void EyerWandTimeLineWidget::mouseReleaseEvent(QMouseEvent * event)
    {
        float x = event->x() * 1.0f;
        float y = event->y() * 1.0f;
        timeLine.OnTouchUp(x, y);
        update();
    }

    void EyerWandTimeLineWidget::mouseDoubleClickEvent(QMouseEvent * event)
    {

    }

    void EyerWandTimeLineWidget::mouseMoveEvent(QMouseEvent * event)
    {
        float x = event->x() * 1.0f;
        float y = event->y() * 1.0f;
        timeLine.OnTouchMove(x, y);
        update();
    }

}
