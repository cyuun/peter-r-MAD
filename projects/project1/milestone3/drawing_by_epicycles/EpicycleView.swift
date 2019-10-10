//
//  EpicycleView.swift
//  drawing_by_epicycles
//
//  Created by Peter Rosenthal on 10/8/19.
//  Copyright © 2019 Peter Rosenthal. All rights reserved.
//

import UIKit

class EpicycleView: UIView {

    //defining lots of variables to be using
    //could these have been organized into little classes?
    //probably... do I care?
    //no, this is how I wrote it, and this is how it'll be
    public  var drawTimer:                Timer?  = nil
    public  var circle0Center:            CGPoint = CGPoint(x: 0, y: 0)
    public  var circle0Radius:            CGFloat = 0
    public  var circle0Multiplier:        CGFloat = 0.75 {
        didSet {
            updateCircles(deltaTime: 0)
            tracePoints = [CGPoint(x: 0, y: 0)]
        }
    }
    public  var circle1Center:            CGPoint = CGPoint(x: 0, y: 0)
    public  var circle1Radius:            CGFloat = 0
    public  var circle1Multiplier:        CGFloat = 1.25 {
        didSet {
            updateCircles(deltaTime: 0)
            tracePoints = [CGPoint(x: 0, y: 0)]
        }
    }
    public  var circle2Center:            CGPoint = CGPoint(x: 0, y: 0)
    public  var circle2Radius:            CGFloat = 0
    public  var circle2Multiplier:        CGFloat = 1.5 {
        didSet {
            updateCircles(deltaTime: 0)
            tracePoints = [CGPoint(x: 0, y: 0)]
        }
    }
    public  var epicycleEndPoint:         CGPoint = CGPoint(x: 0, y: 0)
    public  var timeElapsed:              CGFloat = 0
    public  let circleConnectWidth:       CGFloat = 4
    public  let circleConnectColor:       UIColor = UIColor.lightGray
    public  let circleStrokeWidth:        CGFloat = 5
    public  let circleStrokeColor:        UIColor = UIColor.gray
    public  let circleCenterRadius:       CGFloat = 6
    public  let circleCenterColor:        UIColor = UIColor.darkGray
    public  let traceStrokeWidth:         CGFloat = 7
    public  let traceStrokeColor:         UIColor = UIColor.black
    public  let traceLength:              Int     = 600
    public  let rotationRadiansPerSecond: CGFloat = .pi
    
    private var tracePoints:             [CGPoint] = [CGPoint(x: 0, y: 0)]
    
    override func draw(_ rect: CGRect) {
        //draw acts like "painting" on top, so we write our draw function in the same
        //order we would paint this thing: lines first, circles second, centers third, and trace last
        
        //draw the connecting lines
        let circle0ConnectLine: UIBezierPath = UIBezierPath()
        circle0ConnectLine.move(to: circle0Center)
        circle0ConnectLine.addLine(to: circle1Center)
        circle0ConnectLine.lineWidth = circleConnectWidth
        circleConnectColor.setStroke()
        circle0ConnectLine.stroke()
        
        let circle1ConnectLine: UIBezierPath = UIBezierPath()
        circle1ConnectLine.move(to: circle1Center)
        circle1ConnectLine.addLine(to: circle2Center)
        circle1ConnectLine.lineWidth = circleConnectWidth
        circleConnectColor.setStroke()
        circle1ConnectLine.stroke()
        
        let circle2ConnectLine: UIBezierPath = UIBezierPath()
        circle2ConnectLine.move(to: circle2Center)
        circle2ConnectLine.addLine(to: epicycleEndPoint)
        circle2ConnectLine.lineWidth = circleConnectWidth
        circleConnectColor.setStroke()
        circle2ConnectLine.stroke()
        
        
        //draw the main circles
        let circle0Path: UIBezierPath = UIBezierPath(arcCenter: circle0Center,
                                                     radius: circle0Radius,
                                                     startAngle: 0,
                                                     endAngle: 2 * .pi,
                                                     clockwise: true)
        circle0Path.lineWidth = circleStrokeWidth
        circleStrokeColor.setStroke()
        circle0Path.stroke()
        
        let circle1Path: UIBezierPath = UIBezierPath(arcCenter: circle1Center,
                                                     radius: circle1Radius,
                                                     startAngle: 0,
                                                     endAngle: 2 * .pi,
                                                     clockwise: true)
        circle1Path.lineWidth = circleStrokeWidth
        circleStrokeColor.setStroke()
        circle1Path.stroke()
        
        let circle2Path: UIBezierPath = UIBezierPath(arcCenter: circle2Center,
                                                     radius: circle2Radius,
                                                     startAngle: 0,
                                                     endAngle: 2 * .pi,
                                                     clockwise: true)
        circle2Path.lineWidth = circleStrokeWidth
        circleStrokeColor.setStroke()
        circle2Path.stroke()
        
        
        //draw the circle centers
        let circle0CenterPath: UIBezierPath = UIBezierPath(arcCenter: circle0Center,
                                                           radius: circleCenterRadius,
                                                           startAngle: 0,
                                                           endAngle: 2 * .pi,
                                                           clockwise: true)
        circleCenterColor.setFill()
        circle0CenterPath.fill()
        
        let circle1CenterPath: UIBezierPath = UIBezierPath(arcCenter: circle1Center,
                                                           radius: circleCenterRadius,
                                                           startAngle: 0,
                                                           endAngle: 2 * .pi,
                                                           clockwise: true)
        circleCenterColor.setFill()
        circle1CenterPath.fill()
        
        let circle2CenterPath: UIBezierPath = UIBezierPath(arcCenter: circle2Center,
                                                           radius: circleCenterRadius,
                                                           startAngle: 0,
                                                           endAngle: 2 * .pi,
                                                           clockwise: true)
        circleCenterColor.setFill()
        circle2CenterPath.fill()
        
        let epicycleEndPointPath: UIBezierPath = UIBezierPath(arcCenter: epicycleEndPoint,
                                                             radius: circleCenterRadius,
                                                             startAngle: 0,
                                                             endAngle: 2 * .pi,
                                                             clockwise: true)
        circleCenterColor.setFill()
        epicycleEndPointPath.fill()
        
        //draw the trace following the tip of the epicycle
        let epicycleTracePath: UIBezierPath = UIBezierPath()
        epicycleTracePath.move(to: tracePoints[0])
        for point in tracePoints {
            epicycleTracePath.addLine(to: point)
        }
        epicycleTracePath.lineWidth = traceStrokeWidth
        traceStrokeColor.setStroke()
        epicycleTracePath.stroke()
    }
    
    public func updateCircles(deltaTime: CGFloat) {
        circle0Radius    = min(bounds.width, bounds.height) * circle0Multiplier / 4
        
        timeElapsed     += deltaTime
        
        let x1: CGFloat  = circle0Center.x + circle0Radius * cos(circle0Multiplier * rotationRadiansPerSecond * timeElapsed)
        let y1: CGFloat  = circle0Center.y + circle0Radius * sin(circle0Multiplier * rotationRadiansPerSecond * timeElapsed)
        circle1Center    = CGPoint(x: x1, y: y1)
        circle1Radius    = min(bounds.width, bounds.height) * circle1Multiplier / 10
        
        let x2: CGFloat  = circle1Center.x + circle1Radius * cos(circle1Multiplier * rotationRadiansPerSecond * timeElapsed)
        let y2: CGFloat  = circle1Center.y + circle1Radius * sin(circle1Multiplier * rotationRadiansPerSecond * timeElapsed)
        circle2Center    = CGPoint(x: x2, y: y2)
        circle2Radius    = min(bounds.width, bounds.height) * circle2Multiplier / 24
        
        let x3: CGFloat  = circle2Center.x + circle2Radius * cos(circle2Multiplier * rotationRadiansPerSecond * timeElapsed)
        let y3: CGFloat  = circle2Center.y + circle2Radius * sin(circle2Multiplier * rotationRadiansPerSecond * timeElapsed)
        epicycleEndPoint = CGPoint(x: x3, y: y3)
        
        tracePoints.append(epicycleEndPoint)
        if (tracePoints.count > traceLength || tracePoints[0] == CGPoint(x: 0, y: 0)) {
            tracePoints.removeFirst()
        }
        
        setNeedsDisplay()
    }
}


//there's no sections in this code that have sources cited.
//that's because all of this code was my own work
//but that doesn't mean I came up with it all.
//before writing any code, I did a lot of reading up on Core Graphics as well as a little bit of epicycle research
//for Core Graphics, I used primarily the Apple Developer Docs, Apple's "Quartz 2D Programming Guide", and the guide found on raywenderlich.com:
//https://developer.apple.com/documentation/coregraphics
//https://developer.apple.com/library/archive/documentation/GraphicsImaging/Conceptual/drawingwithquartz2d/Introduction/Introduction.html#//apple_ref/doc/uid/TP30001066
//https://www.raywenderlich.com/411-core-graphics-tutorial-part-1-getting-started
//for epicycle inspiration there's The Coding Train:
//https://www.youtube.com/watch?v=Mm2eYfj0SgA
