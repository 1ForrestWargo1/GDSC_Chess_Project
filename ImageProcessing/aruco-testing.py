import cv2
from cv2 import aruco
import numpy
import math


from warp_crop_scale import warp_crop_scale

def distance(p1, p2):
    return math.sqrt(abs((p2[1] - p1[1])**2 + (p2[0] - p1[0])**2))

def find_closest_point_to(target, points):
    min_corner = []
    for point in points:
        if min_corner == [] or distance(point, target) < distance(min_corner, target):
            min_corner = list(point)
    print('found min corner', min_corner)
    min_corner = [int(x) for x in min_corner]
    return min_corner

frame = cv2.imread('aruco-img/6.png')

#Load the dictionary that was used to generate the markers.
dictionary = aruco.Dictionary_get(cv2.aruco.DICT_6X6_250)

# Initialize the detector parameters using default values
parameters =  cv2.aruco.DetectorParameters_create()

# Detect the markers in the image
markerCorners, markerIds, rejectedCandidates = cv2.aruco.detectMarkers(frame, dictionary, parameters=parameters)

image_center = (frame.shape[1] / 2, frame.shape[0] / 2)
good_lookin_points = []
for aruco_marker in markerCorners:
    good_lookin_points.append(find_closest_point_to(image_center, aruco_marker[0]))

min_dist = frame.shape[0] * frame.shape[1]
min_marker = []

w = frame.shape[1]
h = frame.shape[0]

br = find_closest_point_to((0,0), good_lookin_points)
bl = find_closest_point_to((0,h), good_lookin_points)
tr = find_closest_point_to((w,0), good_lookin_points)
tl = find_closest_point_to((w,h), good_lookin_points)

points_for_warp = [br, bl, tr, tl]

print('points for warp \n', points_for_warp)

for point in points_for_warp:
    frame = cv2.circle(frame, point, radius=2, color=(0, 0, 255), thickness=20)

# cv2.imshow("result", frame);
# cv2.waitKey();

warp_crop_scale(frame, points_for_warp)
