from fastapi import FastAPI, UploadFile, File
from fastapi.responses import JSONResponse
import shutil
import os
from ultralytics import YOLO
import cv2

app = FastAPI()

# 디렉토리 설정
UPLOAD_DIR = "app/uploads"
RESULTS_DIR = "app/results"
MODEL_PATH = "app/models/yolo_model.pt"

os.makedirs(UPLOAD_DIR, exist_ok=True)
os.makedirs(RESULTS_DIR, exist_ok=True)

# YOLO 모델 로드
model = YOLO(MODEL_PATH)


@app.post("/ImageSearch/")
async def upload_image(file: UploadFile = File(...)):
    try:
        # 1. 이미지 저장
        image_path = os.path.join(UPLOAD_DIR, file.filename)
        with open(image_path, "wb") as buffer:
            shutil.copyfileobj(file.file, buffer)

        # 2. YOLO 모델 실행
        image = cv2.imread(image_path)
        results = model(image)

        # 3. 결과 처리
        detections = []
        for idx, box in enumerate(results[0].boxes, start=1):
            x1, y1, x2, y2 = map(int, box.xyxy[0])
            confidence = float(box.conf[0])
            class_id = int(box.cls[0])
            detections.append({
                "className": model.names[class_id],
                "confidence": confidence,
                "x1": x1, "y1": y1, "x2": x2, "y2": y2
            })

        # 4. JSON 응답 반환
        response = {
            "code": "200",
            "message": "success",
            "data": detections
        }
        return JSONResponse(content=response)

    except Exception as e:
        return JSONResponse(
            content={
                "code": "500",
                "message": f"Error processing file: {str(e)}",
                "data": None
            },
            status_code=500
        )
