import cv2

img = cv2.imread("img/img0.jpg", cv2.IMREAD_GRAYSCALE)

cv2.imshow('original img', img)
cv2.waitKey(0)
cv2.destroyAllWindows()

dimentions = (256, 256)
img_scaled = cv2.resize(img, dimentions)

cv2.imshow('img scaled', img_scaled)
cv2.waitKey(0)
cv2.destroyAllWindows()
