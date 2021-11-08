from typing import OrderedDict
import cv2
from cv2 import aruco
import numpy

from warp_crop_scale import warp_crop_scale

frame = cv2.imread('aruco-img/2.JPG')

#Load the dictionary that was used to generate the markers.
dictionary = aruco.Dictionary_get(cv2.aruco.DICT_6X6_250)

# Initialize the detector parameters using default values
parameters =  cv2.aruco.DetectorParameters_create()

# Detect the markers in the image
markerCorners, markerIds, rejectedCandidates = cv2.aruco.detectMarkers(frame, dictionary, parameters=parameters)

print(markerCorners)

print(len(markerCorners))

for marker in markerCorners:
    for corner in marker[0]:
        coord = (int(corner[0]), int(corner[1]))
        print(coord)

#cube order: BR, BL, TR, TL
#point order: TL, TR, BR, BL

m = markerCorners

br = (int(m[0][0][0][0]), int(m[0][0][0][1]))
bl = (int(m[1][0][1][0]), int(m[1][0][1][1]))
tr = (int(m[2][0][3][0]), int(m[2][0][3][1]))
tl = (int(m[3][0][2][0]), int(m[3][0][2][1]))

print('points')
points_for_warp = [br, bl, tr, tl]

print(points_for_warp)

for i in points_for_warp:
    frame = cv2.circle(frame, i, radius=2, color=(0, 0, 255), thickness=20)

cv2.imshow("result", frame);
cv2.waitKey();

warp_crop_scale(frame, points_for_warp)
