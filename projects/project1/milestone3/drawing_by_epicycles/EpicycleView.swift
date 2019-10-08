//
//  EpicycleView.swift
//  drawing_by_epicycles
//
//  Created by Peter Julian Rosenthal on 10/8/19.
//  Copyright © 2019 Peter Julian Rosenthal. All rights reserved.
//

import UIKit

class EpicycleView: UIView {

    public var drawTimer:       Timer?  = nil
    public var circle0Center:   CGPoint = CGPoint(x: 0, y: 0)
    public var circle0SizeMult: CGFloat = 1 {
        didSet {
            setNeedsDisplay();
        }
    }
    public var circle1Center:   CGPoint = CGPoint(x: 0, y: 0)
    public var circle1SizeMult: CGFloat = 1 {
        didSet {
            setNeedsDisplay();
        }
    }
    let circleStrokeWidth:      CGFloat = 8
    let circleStrokeColor:      UIColor = UIColor.black
    
    override func draw(_ rect: CGRect) {
        let circle0Radius: CGFloat      = min(bounds.width, bounds.height) * circle0SizeMult / 4
        let circle0Path:   UIBezierPath = UIBezierPath(arcCenter: circle0Center,
                                                       radius: circle0Radius,
                                                       startAngle: 0,
                                                       endAngle: 2 * .pi,
                                                       clockwise: true)
        circle0Path.lineWidth = circleStrokeWidth
        circleStrokeColor.setStroke()
        circle0Path.stroke()
    }
    
    public func updateCenters() {
        circle1Center = 
    }
}
