#-------------------------------------------------
#
# Project created by QtCreator 2020-07-01T14:14:44
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = EyerWandEditorQt
TEMPLATE = app

# The following define makes your compiler emit warnings if you use
# any feature of Qt which has been marked as deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if you use deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

INCLUDEPATH += ../../Lib/EyerVideoWand/include
INCLUDEPATH += ../../Lib/EyerLib/include

CONFIG += c++11

SOURCES += \
        main.cpp \
        MainWindow.cpp \
        View/EyerWandTimeLineWidget.cpp

HEADERS += \
        MainWindow.h \
        View/EyerWandTimeLineWidget.hpp

FORMS += \
        MainWindow.ui


LIBS += -L../../Lib/EyerVideoWand/lib -lEyerWand
LIBS += -L../../Lib/EyerLib/lib -lEyerLib

LIBS += -L../../Lib/ffmpeg_install/lib -lavcodec -lavutil -lavformat -lswscale -lswresample
LIBS += -L../../Lib/x264_install/lib -lx264
LIBS += -L../../Lib/freetype_install/lib -lfreetype

LIBS += -lz -llzma -lbz2 -liconv

LIBS += -framework AudioToolbox
LIBS += -framework VideoDecodeAcceleration
LIBS += -framework Security
LIBS += -framework CoreGraphics
LIBS += -framework CoreMedia
LIBS += -framework Cocoa
LIBS += -framework VideoToolbox
LIBS += -framework AVFoundation
LIBS += -framework CoreFoundation
LIBS += -framework CoreVideo
LIBS += -framework OpenGL
LIBS += -framework AppKit

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target
