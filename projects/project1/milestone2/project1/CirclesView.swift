//
//  CirclesView.swift
//  project1
//
//  Created by Peter Rosental on 30/09/2019.
//  Copyright © 2019 Peter Rosental. All rights reserved.
//

import UIKit

class CirclesView: UIView {
    
    let lineSize: CGFloat = 12
    
    @IBInspectable var circleSize: CGFloat = 0.25 {
        didSet {
            setNeedsDisplay()
        }
    }
    
    override func draw(_ rect: CGRect) {
        let center = CGPoint(x: bounds.width / 2, y: bounds.height / 2)
        let radius = min(bounds.width, bounds.height) * circleSize - lineSize / 2
        
        let path = UIBezierPath(arcCenter: center, radius: radius, startAngle: 0, endAngle: .pi * 2, clockwise: true)
        path.lineWidth = lineSize
        UIColor.green.setStroke()
        path.stroke()
    }

}
