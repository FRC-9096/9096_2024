package frc.utils;

public class SwerveUtils {

    // lerp with step size, (arbitrary val -> new val)
    public static double StepTowards(double _current, double _target, double _stepsize) {
        if (Math.abs(_current - _target) <= _stepsize) {
            return _target;
        }
        else if (_target < _current) {
            return _current - _stepsize;
        }
        else {
            return _current + _stepsize;
        }
    }

    // circular lerp with step size, (angle -> new angle)
    public static double StepTowardsCircular(double _current, double _target, double _stepsize) {
        _current = WrapAngle(_current); // mod 2pi
        _target = WrapAngle(_target);

        double stepDirection = Math.signum(_target - _current);
        double difference = Math.abs(_current - _target);
        
        if (difference <= _stepsize) {
            return _target;
        }
        else if (difference > Math.PI) {
            if (_current + 2*Math.PI - _target < _stepsize || _target + 2*Math.PI - _current < _stepsize) {
                return _target;
            }
            else {
                return WrapAngle(_current - stepDirection * _stepsize);
            }

        }
        else {
            return _current + stepDirection * _stepsize;
        }
    }

    public static double AngleDifference(double _angleA, double _angleB) {
        double difference = Math.abs(_angleA - _angleB);
        return difference > Math.PI? (2 * Math.PI) - difference : difference;
    }


    // mod 2pi, (angle -> angle)
    public static double WrapAngle(double _angle) {
        double twoPi = 2*Math.PI;

        if (_angle == twoPi) {
            return 0.0;
        }
        else if (_angle > twoPi) {
            return _angle - twoPi*Math.floor(_angle / twoPi);
        }
        else if (_angle < 0.0) {
            return _angle + twoPi*(Math.floor((-_angle) / twoPi)+1);
        }
        else {
            return _angle;
        }
    }
}